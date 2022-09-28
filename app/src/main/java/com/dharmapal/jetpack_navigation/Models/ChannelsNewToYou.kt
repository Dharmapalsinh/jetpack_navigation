package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class ChannelsNewToYou(
    @SerializedName("channel")
    val channel: ChannelX?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("extensions")
    val extensions: List<String>?,
    @SerializedName("length")
    val length: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position_on_page")
    val positionOnPage: Any?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailX?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("views")
    val views: Int?
)