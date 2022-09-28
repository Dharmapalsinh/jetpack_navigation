package com.dharmapal.jetpack_navigation.Models


import com.google.gson.annotations.SerializedName

data class SearchParameters(
    @SerializedName("engine")
    val engine: String?,
    @SerializedName("search_query")
    val searchQuery: String?
)