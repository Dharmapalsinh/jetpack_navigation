package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class SearchInformation(
    @SerializedName("total_results")
    val totalResults: Long?,
    @SerializedName("video_results_state")
    val videoResultsState: String?
)