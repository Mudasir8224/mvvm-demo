package com.example.mvvmdemo.data.repository.api

import com.example.mvvmdemo.data.repository.model.PhotoItem
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getPhotos(): List<PhotoItem>
}