package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class PlaylistResults(
    @SerializedName("channel")
    val channel: ChannelXXXX?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("position_on_page")
    val positionOnPage: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video_count")
    val videoCount: Int?,
    @SerializedName("videos")
    val videos: List<Video>?
)