package com.example.mvvmdemo.ui.viewmodel.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmdemo.R
import com.example.mvvmdemo.data.repository.model.Photo
import com.example.mvvmdemo.data.repository.model.PhotoItem
import com.example.mvvmdemo.databinding.PhotoRvBinding


class PhotoAdapter(
    private val context: Context,
    private var list: ArrayList<PhotoItem> = arrayListOf()
) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoViewHolder {
        return PhotoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.photo_rv, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        holder.bind(position)
    }

    fun loadData(data: ArrayList<PhotoItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PhotoViewHolder(private var binding: PhotoRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            list[position].let {
                binding.textView.text = it.title
                Log.d("TAG", it.url)
                Glide.with(context).load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg").into(binding.imageView)
            }
        }
    }

}