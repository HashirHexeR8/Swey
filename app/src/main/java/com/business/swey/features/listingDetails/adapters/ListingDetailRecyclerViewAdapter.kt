package com.business.swey.features.listingDetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.business.swey.databinding.ListingDetailProductImageRecyclerViewItemBinding

class ListingDetailRecyclerViewAdapter: Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewBinding = ListingDetailProductImageRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListingDetailRecyclerViewItemViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class ListingDetailRecyclerViewItemViewHolder(itemViewBinding: ListingDetailProductImageRecyclerViewItemBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

    }
}