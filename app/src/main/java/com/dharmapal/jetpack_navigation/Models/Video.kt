package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("length")
    val length: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?
)