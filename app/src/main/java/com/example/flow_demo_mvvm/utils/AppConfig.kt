package com.example.flow_demo_mvvm.utils

import com.example.flow_demo_mvvm.network.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AppConfig {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun ApiService():ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
}