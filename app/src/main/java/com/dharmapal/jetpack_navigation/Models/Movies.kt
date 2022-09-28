package com.dharmapal.jetpack_navigation.Models


import androidx.recyclerview.widget.DiffUtil
import com.dharmapal.gatetouch_task.MyMovies
import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("ads_results")
    val adsResults: List<AdsResult>?,
    @SerializedName("channels_new_to_you")
    val channelsNewToYou: List<ChannelsNewToYou>?,
    @SerializedName("movie_results")
    val movieResults: List<MovieResult>?,
    @SerializedName("pagination")
    val pagination: Pagination?,
    @SerializedName("people_also_watched")
    val peopleAlsoWatched: List<PeopleAlsoWatched>?,
    @SerializedName("playlist_results")
    val playlistResults: List<PlaylistResults>?,
    @SerializedName("search_information")
    val searchInformation: SearchInformation?,
    @SerializedName("search_metadata")
    val searchMetadata: SearchMetadata?,
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters?,
    @SerializedName("serpapi_pagination")
    val serpapiPagination: SerpapiPagination?,
    @SerializedName("video_results")
    val videoResults: List<VideoResult>?
)

object diff: DiffUtil.ItemCallback<VideoResult>(){
    override fun areItemsTheSame(oldItem: VideoResult, newItem: VideoResult): Boolean {
        return oldItem.title==newItem.title
    }

    override fun areContentsTheSame(oldItem: VideoResult, newItem: VideoResult): Boolean {
        return oldItem==newItem
    }
}