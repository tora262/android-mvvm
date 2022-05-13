package com.example.learnandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.example.learnandroid.R;
import com.example.learnandroid.common.Constants;
import com.example.learnandroid.databinding.ActivityMainBinding;
import com.example.learnandroid.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

/**
 * @author hieutt (tora262)
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = MainActivity.class.getName();
    private int mChoose = Constants.LensFacing.BACK;
    private ActivityMainBinding mBinding;
    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initView();
    }

    private void initView() {
        observe();
        mBinding.btnGetUser.setOnClickListener(view -> {
            viewModel.getUser(2L);
        });

        mBinding.radioArm.setOnCheckedChangeListener(this);
        mBinding.radioArmpit.setOnCheckedChangeListener(this);
        mBinding.radioFace.setOnCheckedChangeListener(this);
        mBinding.radioLeg.setOnCheckedChangeListener(this);

        mBinding.btnLaunch.setOnClickListener(view -> {
            Intent sentIntent = new Intent(MainActivity.this, CameraXPreviewActivity.class);
            sentIntent.putExtra(Constants.LENS_FACING, mChoose);
            startActivity(sentIntent);
        });
    }

    private void observe() {
        viewModel.getErrorMessageLiveData().observe(this, errorMessage-> {
            Timber.d("observe: errorMessage = " + errorMessage);
        });

        viewModel.getUserLiveData().observe(this, userResponse -> {
            Timber.d("observe: userResponse = " + userResponse);
            mBinding.tvEmail.setText(userResponse.toString());
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Timber.d(intent.getAction());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Timber.d("choose: " + compoundButton.getText());
        mBinding.btnLaunch.setEnabled(true);
        if (b) {
            if (compoundButton.getText().toString().equals(getResources().getString(R.string.face))
                    || compoundButton.getText().toString().equals(getResources().getString(R.string.armpit))) {
                mChoose = Constants.LensFacing.FRONT;
            } else {
                mChoose = Constants.LensFacing.BACK;
            }
        }
    }
}