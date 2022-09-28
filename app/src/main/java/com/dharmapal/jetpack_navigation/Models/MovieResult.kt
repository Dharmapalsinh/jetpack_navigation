package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("channel")
    val channel: ChannelXX?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("extensions")
    val extensions: List<String>?,
    @SerializedName("info")
    val info: List<String>?,
    @SerializedName("length")
    val length: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position_on_page")
    val positionOnPage: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?
)