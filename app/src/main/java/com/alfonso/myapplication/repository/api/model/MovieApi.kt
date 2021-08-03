package com.alfonso.myapplication.repository.api.model

import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieApi(val adult: Boolean, val backdrop_path : String? = "", val genre_ids : List<Int>, val id : Int,
                    val original_language : String, val original_title : String, val overview : String,
                    val popularity: Double, val poster_path: String, val release_date : String? = "",
                    val title: String, val video: Boolean, val vote_average: Double, val vote_count: Int, ) {


    fun toMovieDB(type : TypeMovie) : MovieDB {
        return MovieDB(0,id,adult,backdrop_path,genre_ids.joinToString(separator = ","),original_language,
        original_title,overview,popularity,poster_path,release_date,title,video,vote_average,vote_count,type)
    }

}