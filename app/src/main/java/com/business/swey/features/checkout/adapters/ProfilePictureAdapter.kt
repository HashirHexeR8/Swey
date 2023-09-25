package com.business.swey.features.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.SweyUserDto
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.ItemRoundPictureBinding

class ProfilePictureAdapter : ListAdapter<SweyUserDto, RecyclerView.ViewHolder>(UserDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SweyUserDto -> Enum.UserPictureItemType.USER.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Enum.UserPictureItemType.USER.value -> UserPictureItemViewHolder(
                ItemRoundPictureBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Enum.UserPictureItemType.USER.value -> {
                val viewHolder = holder as? UserPictureItemViewHolder?
                viewHolder?.bind(
                    getItem(position)
                )
            }
        }
    }

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class UserPictureItemViewHolder(private val binding: ItemRoundPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SweyUserDto) {
//            binding.ivPicture.setImageResource(data.imageRes)
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<SweyUserDto>() {
    override fun areItemsTheSame(oldItem: SweyUserDto, newItem: SweyUserDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SweyUserDto, newItem: SweyUserDto): Boolean {
        return oldItem.id == newItem.id
    }
}
