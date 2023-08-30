package com.example.androidapp.utility;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api
{

      public static Retrofit getRetrofitBuilder(Context context) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100,TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true).
                            build();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppUtils.BASE_URL)
                    //.addCallAdapterFactory(RetryCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofit;

        }

    }

