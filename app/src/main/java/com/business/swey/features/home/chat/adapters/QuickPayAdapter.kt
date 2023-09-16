package com.business.swey.features.home.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.PersonQuickPayDto
import com.business.swey.core.utils.Enum.QuickPayItemType
import com.business.swey.databinding.ItemChatQuickPayBinding

class QuickPayAdapter(
    private var onSelectionCallback: (PersonQuickPayDto) -> Unit,
    private var list: List<PersonQuickPayDto>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is PersonQuickPayDto -> QuickPayItemType.QUICK_PAY_ICON.value
            else -> super.getItemViewType(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QuickPayItemType.QUICK_PAY_ICON.value -> QuickPayChatViewHolder(
                ItemChatQuickPayBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            QuickPayItemType.QUICK_PAY_ICON.value -> {
                val viewHolder = holder as? QuickPayChatViewHolder?
                viewHolder?.bind(
                    list[position],
                    onSelectionCallback
                )
            }
        }
    }

    override fun getItemCount() = list.size

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class QuickPayChatViewHolder(private val binding: ItemChatQuickPayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PersonQuickPayDto, onSelectionCallback: (PersonQuickPayDto) -> Unit) {
            binding.tvTitle.text = data.title
            binding.ivUserImage.setImageResource(data.drawableId)
            binding.cardView.setOnClickListener {
                onSelectionCallback.invoke(data)
            }
        }
    }
}