package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class AdsResult(
    @SerializedName("channel")
    val channel: Channel?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("length")
    val length: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position_on_page")
    val positionOnPage: Int?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("views")
    val views: Int?
)