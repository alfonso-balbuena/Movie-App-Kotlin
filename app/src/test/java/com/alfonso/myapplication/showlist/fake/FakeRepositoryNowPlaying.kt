package com.alfonso.myapplication.showlist.fake

import com.alfonso.myapplication.repository.IRepositoryNowPlayingMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.model.Movie

class FakeRepositoryNowPlaying : IRepositoryNowPlayingMovie {

    var returnError : Boolean = false

    override suspend fun getNowPlayingMovies(): RepositoryResult {
        return if (returnError) RepositoryResult.Error
        else RepositoryResult.Success(listOf( Movie(1,23,false,"",""
            ,"","","",1.0,"","","",false,2.3,1) )  )
    }

    override suspend fun refresh(): RepositoryResult {
        return if (returnError) RepositoryResult.Error
        else RepositoryResult.Success(listOf( Movie(2,10,false,"",""
            ,"","","",1.0,"","","",false,2.3,1) )  )
    }
}