package com.alfonso.myapplication.repository.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowPlayingRequest(val page: Int, val results: List<MovieApi>)
