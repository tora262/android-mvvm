package com.example.learnandroid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.learnandroid.databinding.ActivityMainBinding;
import com.example.learnandroid.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

/**
 * @author hieutt (tora262)
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    ActivityMainBinding mBinding;
    MainViewModel viewModel;
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

        mBinding.btnLaunch.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
    }

    private void observe() {
        viewModel.getErrorMessageLiveData().observe(this, errorMessage-> {
            Log.d(TAG, "observe: errorMessage = " + errorMessage);
        });

        viewModel.getUserLiveData().observe(this, userResponse -> {
            Log.d(TAG, "observe: userResponse = " + userResponse);
            mBinding.tvEmail.setText(userResponse.toString());
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Timber.d(intent.getAction());
    }
}