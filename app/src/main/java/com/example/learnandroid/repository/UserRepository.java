package com.example.learnandroid.repository;

import com.example.learnandroid.data.network.UserApiService;
import com.example.learnandroid.data.network.response.UserResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * @author hieutt (tora262)
 */
public class UserRepository {
    private UserApiService userApiService;

    @Inject
    public UserRepository(UserApiService userApiService) {
        this.userApiService = userApiService;
    }

    public Call<UserResponse> getUserById(Long id) {
        return userApiService.getUser(id);
    }
}
