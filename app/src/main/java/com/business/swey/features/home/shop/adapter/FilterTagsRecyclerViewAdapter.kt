package com.business.swey.features.home.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.R
import com.business.swey.core.models.FilterItemDto
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.ItemFilterTagBinding

class FilterTagsRecyclerViewAdapter : ListAdapter<FilterItemDto, RecyclerView.ViewHolder>(ChatDiffCallback()) {

    private var onSelected: ((FilterItemDto) -> Unit)? = null
    private var selectedFilters = mutableMapOf<Int, Int>()

    fun initialize(
        list: List<FilterItemDto>, defaultSelectedIndex: Int = 0,
        onSelected: ((FilterItemDto) -> Unit)? = null
    ) {
        submitList(list)
        selectedFilters[defaultSelectedIndex] = defaultSelectedIndex
        this.onSelected = onSelected
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FilterItemDto -> Enum.FilterTagItemType.FILTER_TAG.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Enum.FilterTagItemType.FILTER_TAG.value -> FilterTagItemViewHolder(
                ItemFilterTagBinding.inflate(LayoutInflater.from(parent.context))
            )

            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            Enum.FilterTagItemType.FILTER_TAG.value -> {
                val viewHolder = holder as? FilterTagItemViewHolder?
                viewHolder?.bind(
                    data = getItem(position),
                    position = position,
                    isSelected = selectedFilters.containsKey(position),
                    isFirst = position == 0,
                    onSelected = ::onSelectedItem
                )
            }
        }
    }

    private fun onSelectedItem(index: Int){
        if (selectedFilters.containsKey(index))
            selectedFilters.remove(index)
        else
            selectedFilters[index] = index
        onSelected?.invoke(currentList[index])
        notifyItemChanged(index)
    }

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class FilterTagItemViewHolder(private val binding: ItemFilterTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: FilterItemDto,
            position: Int,
            isSelected: Boolean = false,
            isFirst: Boolean = false,
            onSelected: ((Int) -> Unit)?
        ) {
            binding.tagName = data.tagName

            if (isSelected) {
                binding.tvTag.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                binding.layoutText.background = ContextCompat.getDrawable(itemView.context, R.drawable.gradient_filter_tag)
            } else {
                binding.tvTag.setTextColor(ContextCompat.getColor(itemView.context, R.color.primary_text_color))
                binding.layoutText.background = ContextCompat.getDrawable(itemView.context, R.color.tip_background_color)
            }

            binding.cardParent.setOnClickListener {
                onSelected?.invoke(position)
            }
        }
    }
}

class ChatDiffCallback : DiffUtil.ItemCallback<FilterItemDto>() {
    override fun areItemsTheSame(oldItem: FilterItemDto, newItem: FilterItemDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilterItemDto, newItem: FilterItemDto): Boolean {
        return oldItem.id == newItem.id
    }
}

