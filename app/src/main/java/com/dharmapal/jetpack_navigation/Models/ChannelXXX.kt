package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class ChannelXXX(
    @SerializedName("link")
    val link: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("verified")
    val verified: Boolean?
)