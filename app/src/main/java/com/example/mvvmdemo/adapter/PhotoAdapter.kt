package com.example.mvvmdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.PhotoRvBinding
import com.example.mvvmdemo.model.PhotoItem

class PhotoAdapter(private var list: ArrayList<PhotoItem> = arrayListOf()) :
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
                // binding.imageView.setImageResource(it.url)
            }
        }

    }

}