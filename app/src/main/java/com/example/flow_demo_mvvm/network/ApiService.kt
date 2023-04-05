package com.example.flow_demo_mvvm.network

import com.example.flow_demo_mvvm.model.Comment_Model
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("comments/{id}")
    suspend fun getcomments(@Path("id") id:Int) :Comment_Model
}