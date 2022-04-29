package com.example.learnandroid.data.network.response;

import com.example.learnandroid.data.network.models.User;

public class UserResponse {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "data=" + data+
                '}';
    }
}
