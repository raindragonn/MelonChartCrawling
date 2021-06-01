package com.raindragonn.crawling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.raindragonn.crawling.databinding.ItemChartBinding

// Created by raindragonn on 2021/06/01.

class ChartAdapter : ListAdapter<ChartModel, ChartAdapter.ViewHolder>(differ) {
    companion object {
        private val differ = object : DiffUtil.ItemCallback<ChartModel>() {
            override fun areItemsTheSame(oldItem: ChartModel, newItem: ChartModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ChartModel, newItem: ChartModel): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).let { inflater ->
            return ViewHolder(ItemChartBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: ItemChartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ChartModel) = with(binding) {
            tvTitle.text = model.title
            tvArtist.text = model.artist

            Glide.with(ivThumbnail)
                .load(model.imgUrl)
                .placeholder(R.drawable.ic_music)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivThumbnail)

        }
    }
}