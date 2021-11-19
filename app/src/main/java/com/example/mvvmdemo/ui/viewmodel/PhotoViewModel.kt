package com.example.mvvmdemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvmdemo.data.repository.Repository
import com.example.mvvmdemo.utils.Resource
import kotlinx.coroutines.Dispatchers

class PhotoViewModel(private val repository: Repository) : ViewModel() {

    fun getPhoto() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPhoto()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred"))
        }

    }

}