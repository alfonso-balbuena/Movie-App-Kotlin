package com.alfonso.myapplication.showlist.fake

import com.alfonso.myapplication.repository.IRepositoryPopularMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.model.Movie

class FakeRepositoryPopular : IRepositoryPopularMovie {

    var returnError : Boolean = false

    override suspend fun getPopularMovies(): RepositoryResult {
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