package com.business.swey.listingDetails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.business.swey.R
import com.business.swey.databinding.SizeTileFilterRecyclerViewItemBinding
import com.business.swey.models.SizeTileFilterItemDTO

class SizeTileFilterRecyclerViewAdapter: Adapter<RecyclerView.ViewHolder>() {

    private var mItemsDataSource = ArrayList<SizeTileFilterItemDTO>()

    fun setDataSource(itemDataSource: ArrayList<SizeTileFilterItemDTO>) {
        mItemsDataSource = itemDataSource
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewBinding = SizeTileFilterRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizeTileRecyclerViewItemViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemData = mItemsDataSource[position]
        if (holder is SizeTileRecyclerViewItemViewHolder) {
            if (itemData.sizeType == com.business.swey.utils.Enum.SizeType.normal) {
                holder.itemViewBinding.tvSizeTileSizeType.visibility = View.GONE
            }
            else {
                holder.itemViewBinding.tvSizeTileSizeType.visibility = View.VISIBLE
                holder.itemViewBinding.tvSizeTileSizeType.text = itemData.sizeType.value
            }
            holder.itemViewBinding.tvSizeTileSize.text = itemData.size
            try {
                if (itemData.size.toDouble() % 1.0 == 0.0) {
                    holder.itemViewBinding.tvSizeTileSize.text = "${itemData.size.toDouble().toInt()}"
                }
            }
            catch (exception: Exception) {

            }
            holder.itemViewBinding.root.setOnClickListener {
                holder.itemViewBinding.root.isSelected = !holder.itemViewBinding.root.isSelected
                if (holder.itemViewBinding.root.isSelected) {
                    holder.itemViewBinding.tvSizeTileSize.setTextColor(holder.itemView.resources.getColor(R.color.size_text_color_selected))
                    holder.itemViewBinding.tvSizeTileSizeType.setTextColor(holder.itemView.resources.getColor(R.color.size_text_color_selected))
                }
                else {
                    holder.itemViewBinding.tvSizeTileSize.setTextColor(holder.itemView.resources.getColor(R.color.size_text_color_normal))
                    holder.itemViewBinding.tvSizeTileSizeType.setTextColor(holder.itemView.resources.getColor(R.color.size_text_color_normal))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mItemsDataSource.size
    }

    inner class SizeTileRecyclerViewItemViewHolder(val itemViewBinding: SizeTileFilterRecyclerViewItemBinding): RecyclerView.ViewHolder(itemViewBinding.root) {

    }
}