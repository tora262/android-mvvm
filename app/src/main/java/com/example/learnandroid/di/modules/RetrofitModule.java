package com.example.learnandroid.di.modules;

import com.example.learnandroid.data.network.UserApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {
    @Singleton
    @Provides
    Retrofit provideRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    UserApiService getUserApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }

}
