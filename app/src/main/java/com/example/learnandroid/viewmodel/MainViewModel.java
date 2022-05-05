package com.example.learnandroid.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learnandroid.data.network.response.UserResponse;
import com.example.learnandroid.repository.UserRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @author hieutt (tora262)
 */
@HiltViewModel
public class MainViewModel extends ViewModel {
    private UserRepository userRepository;

    private MutableLiveData<UserResponse> userLiveData = new MutableLiveData<>();

    private MutableLiveData<String> errorMessageLiveData = new MutableLiveData<>();

    @Inject
    public MainViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUser(Long id) {
        Call<UserResponse> call = userRepository.getUserById(id);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                Timber.d(response.body().toString());
                userLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Timber.d(t);
                errorMessageLiveData.setValue(t.getMessage());
            }
        });
    }

    public LiveData<UserResponse> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }
}
