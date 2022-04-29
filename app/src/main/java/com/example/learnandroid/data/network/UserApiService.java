package com.example.learnandroid.data.network;

import com.example.learnandroid.data.network.models.User;
import com.example.learnandroid.data.network.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApiService {

    @GET("api/users/{id}")
    Call<UserResponse> getUser(@Path("id") Long id);
}
