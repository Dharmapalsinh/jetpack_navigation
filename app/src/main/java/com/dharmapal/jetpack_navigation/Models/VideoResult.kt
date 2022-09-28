package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("channel")
    val channel: ChannelXXXXX?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("extensions")
    val extensions: List<String>?,
    @SerializedName("length")
    val length: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("live")
    val live: Boolean?,
    @SerializedName("position_on_page")
    val positionOnPage: Int?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailXXX?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("watching")
    val watching: Int?
)