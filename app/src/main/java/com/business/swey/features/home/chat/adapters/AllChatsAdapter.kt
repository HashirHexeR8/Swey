package com.business.swey.features.home.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.PersonChatDto
import com.business.swey.core.models.ChatStatusType
import com.business.swey.core.models.ChatDirection
import com.business.swey.core.utils.Enum.QuickPayItemType
import com.business.swey.databinding.ItemChatPersonBinding

class AllChatsAdapter(
    private var onSelectionCallback: (PersonChatDto) -> Unit,
    private var list: List<PersonChatDto>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is PersonChatDto -> QuickPayItemType.QUICK_PAY_PERSON.value
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QuickPayItemType.QUICK_PAY_PERSON.value -> PersonChatViewHolder(
                ItemChatPersonBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            QuickPayItemType.QUICK_PAY_PERSON.value -> {
                val viewHolder = holder as? PersonChatViewHolder?
                viewHolder?.bind(
                    list[position],
                    onSelectionCallback
                )
            }
        }
    }

    override fun getItemCount() = list.size

    /// ------------------------ Defining ViewHolder ------------------------ ///
    inner class PersonChatViewHolder(private val binding: ItemChatPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PersonChatDto, onSelectionCallback: (PersonChatDto) -> Unit) {
            binding.tvName.text = data.name
            binding.tvLastMessage.text = data.lastMessage.message
            if (data.lastMessage.chatDirection == ChatDirection.SENT && data.lastMessage.messageStatus != null) {
                when (data.lastMessage.messageStatus) {
                    ChatStatusType.DELIVERED -> {
                        binding.tvMessageSent.isVisible = true
                        binding.ivMessageRead.isVisible = false
                        binding.cardMessageCount.isVisible = false
                    }

                    ChatStatusType.SEEN -> {
                        binding.tvMessageSent.isVisible = false
                        binding.ivMessageRead.isVisible = true
                        binding.cardMessageCount.isVisible = false
                    }

                    else -> Unit
                }
            } else {
                if (data.messageCount != null && data.messageCount > 1) {
                    binding.tvMessageSent.isVisible = false
                    binding.ivMessageRead.isVisible = false
                    binding.cardMessageCount.isVisible = true
                    binding.tvMessageCount.text = (data.messageCount - 1).toString()
                }
                else {
                    binding.layoutMessageStatus.isVisible = false
                }
            }
            binding.ivUserImage.setImageResource(data.personDrawableId)
            binding.root.setOnClickListener {
                onSelectionCallback.invoke(data)
            }
        }
    }
}