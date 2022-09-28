package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class ChannelXXXX(
    @SerializedName("link")
    val link: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("verified")
    val verified: Boolean?
)