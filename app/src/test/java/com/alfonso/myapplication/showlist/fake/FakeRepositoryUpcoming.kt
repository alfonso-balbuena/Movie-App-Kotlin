package com.alfonso.myapplication.showlist.fake

import com.alfonso.myapplication.repository.IRepositoryUpcomingMovie
import com.alfonso.myapplication.repository.RepositoryResult
import com.alfonso.myapplication.repository.model.Movie

class FakeRepositoryUpcoming : IRepositoryUpcomingMovie {

    var returnError : Boolean = false

    override suspend fun getUpcomingMovies(): RepositoryResult {
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