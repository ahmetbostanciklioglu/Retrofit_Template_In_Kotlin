package com.retro_in_compose

import retrofit2.http.GET

interface ApiService {
    @GET("endpoint")
    suspend fun getPosts(): List<ResponseData>
}

