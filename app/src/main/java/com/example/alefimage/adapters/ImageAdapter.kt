package com.example.alefimage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.alefimage.R
import com.example.alefimage.databinding.ImageItemBinding
import com.example.alefimage.util.load

class ImageAdapter (
    private val data: LiveData<List<String>>,
    private val onClick: (String) -> Unit
    ): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){
    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ImageItemBinding.bind(view)

        fun bind(item: String) = with(binding) {
            iwLandscape.load(item, R.drawable.ic_error)
            iwLandscape.setOnClickListener { onClick.invoke(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        data.value?.let{
            val item = it[position]
            holder.bind(item)
        }
    }

    override fun getItemCount() = data.value?.size ?: 0
}