package com.alfonso.myapplication.repository.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularRequest(val page: Int, val results: List<BookApi>)
