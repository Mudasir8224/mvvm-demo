package com.example.mvvmdemo.data.repository.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPhotos() = apiService.getPhotos()
}