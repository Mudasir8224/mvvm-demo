package com.example.mvvmdemo.data.repository

import com.example.mvvmdemo.data.repository.api.ApiHelper

class Repository(private val apiHelper: ApiHelper) {

    suspend fun getPhoto() = apiHelper.getPhotos()

}