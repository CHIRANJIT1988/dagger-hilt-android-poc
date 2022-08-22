package com.dagger.hilt.data.api

import com.dagger.hilt.data.models.People
import com.dagger.hilt.data.models.Room
import retrofit2.Response
import retrofit2.http.GET

interface VirginMoneyApi {

    @GET("/api/v1/people")
    suspend fun getPeoples() : Response<List<People>>?

    @GET("/api/v1/rooms")
    suspend fun getRooms() : Response<List<Room>>?
}
