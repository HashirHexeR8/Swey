package com.business.swey.features.home.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.ShopItemDto
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.ItemLayoutShopNowBinding

class ShopNowRecyclerViewAdapter : ListAdapter<ShopItemDto, RecyclerView.ViewHolder>(ShopNowDiffCallback()) {

    private var onClick: ((ShopItemDto) -> Unit)? = null

    fun initialize(
        list: List<ShopItemDto>,
        onClick: ((ShopItemDto) -> Unit)? = null
    ) {
        submitList(list)
        this.onClick = onClick
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ShopItemDto -> Enum.ShopNowItemType.SHOP_NOW.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Enum.ShopNowItemType.SHOP_NOW.value -> ShopNowItemViewHolder(
                ItemLayoutShopNowBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Enum.ShopNowItemType.SHOP_NOW.value -> {
                val viewHolder = holder as? ShopNowItemViewHolder?
                viewHolder?.bind(
                    data = getItem(position),
                    onClick = onClick
                )
            }
        }
    }

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class ShopNowItemViewHolder(private val binding: ItemLayoutShopNowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: ShopItemDto,
            onClick: ((ShopItemDto) -> Unit)?
        ) {
            binding.shopItem = data

            binding.cardParent.setOnClickListener {
                onClick?.invoke(data)
            }
        }
    }
}

class ShopNowDiffCallback : DiffUtil.ItemCallback<ShopItemDto>() {
    override fun areItemsTheSame(oldItem: ShopItemDto, newItem: ShopItemDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ShopItemDto, newItem: ShopItemDto): Boolean {
        return oldItem.id == newItem.id
    }
}

