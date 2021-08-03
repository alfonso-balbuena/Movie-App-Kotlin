package com.alfonso.myapplication.repository.model

import com.alfonso.myapplication.repository.database.model.MovieDB
import com.alfonso.myapplication.repository.database.model.TypeMovie

data class Movie(var id : Long,val idApi : Int, val adult: Boolean, val backdrop_path : String? = "", val genre_ids : String,
                 val original_language : String, val original_title : String, val overview : String,
                 val popularity: Double, val poster_path: String, val release_date : String? = "",
                 val title: String, val video: Boolean, val vote_average: Double, val vote_count: Int,) {


    companion object {
        fun fromMovieDB(movieDB : MovieDB) : Movie {
            return Movie(movieDB.id,movieDB.idApi,movieDB.adult,movieDB.backdrop_path,movieDB.genre_ids,
            movieDB.original_language,movieDB.original_title,movieDB.overview,movieDB.popularity,
            movieDB.poster_path,movieDB.release_date,movieDB.title,movieDB.video,movieDB.vote_average,
            movieDB.vote_count)
        }
    }



}
