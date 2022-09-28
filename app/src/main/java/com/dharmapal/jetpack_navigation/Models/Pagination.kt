package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current")
    val current: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("next_page_token")
    val nextPageToken: String?
)