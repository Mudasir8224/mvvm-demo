package com.example.mvvmdemo.ui.viewmodel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.R
import com.example.mvvmdemo.data.repository.api.ApiHelper
import com.example.mvvmdemo.data.repository.api.RetrofitBuilder
import com.example.mvvmdemo.data.repository.model.Photo
import com.example.mvvmdemo.data.repository.model.PhotoItem
import com.example.mvvmdemo.databinding.ActivityMainBinding
import com.example.mvvmdemo.ui.viewmodel.adapter.PhotoAdapter
import com.example.mvvmdemo.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setRecyclerView()
        getData()

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(PhotoViewModel::class.java)
    }

    private fun setRecyclerView() {
        adapter = PhotoAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }


    private fun getData() {
        viewModel.getPhoto().observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data.let { list: List<PhotoItem>? ->
                            if (list != null) {
                                retrieveList(photos = list)
                            }
                        }
                    }
                    Status.ERROR ->{
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        Log.d("TAG",it.message.toString())
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "loading", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(photos: List<PhotoItem>) {
        adapter.apply {
            loadData(photos as ArrayList<PhotoItem>)
            notifyDataSetChanged()
        }
    }

}