package com.example.flow_demo_mvvm.model

import com.google.gson.annotations.SerializedName

data class Comment_Model(
    val postId : Int ?= null,
    val id : Int? = null,
    val name : String? =null,
    val email : String? = null,

    @SerializedName("body")
    val comment : String? = null
)
