package com.retro_in_compose

import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getData() = apiService.getPosts()
}