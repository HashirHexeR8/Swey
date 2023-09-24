package com.business.swey.features.home.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.ChatTextDto
import com.business.swey.core.models.ChatDirection
import com.business.swey.core.models.ChatDto
import com.business.swey.core.models.ChatImageDto
import com.business.swey.core.models.ChatLocationDto
import com.business.swey.core.models.ChatPayDto
import com.business.swey.core.models.ChatTimestampDto
import com.business.swey.core.models.ChatType
import com.business.swey.core.models.ChatVideoDto
import com.business.swey.core.utils.ChatItemTypes
import com.business.swey.databinding.ItemChatImageReceiveBinding
import com.business.swey.databinding.ItemChatImageSendBinding
import com.business.swey.databinding.ItemChatLocationReceiveBinding
import com.business.swey.databinding.ItemChatLocationSendBinding
import com.business.swey.databinding.ItemChatMessageReceiveBinding
import com.business.swey.databinding.ItemChatMessageSendBinding
import com.business.swey.databinding.ItemChatPayReceiveBinding
import com.business.swey.databinding.ItemChatPaySendBinding
import com.business.swey.databinding.ItemChatTimestampBinding
import com.business.swey.databinding.ItemChatVideoReceiveBinding
import com.business.swey.databinding.ItemChatVideoSendBinding
import com.business.swey.features.home.chat.adapters.viewHolders.ReceiveImageViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.ReceiveLocationViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.ReceivePayViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.ReceiveTextViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.ReceiveVideoViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendImageViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendLocationViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendPayViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendTextViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.SendVideoViewHolder
import com.business.swey.features.home.chat.adapters.viewHolders.TimeStampViewHolder

class ChatMessagesAdapter(
    private val onMessageLongClick: (ChatDto, IntArray) -> Unit,
    private val onMessageClick: (ChatDto) -> Unit
) : ListAdapter<ChatDto, RecyclerView.ViewHolder>(ChatDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            ChatItemTypes.TIMESTAMP -> {
                val binding = ItemChatTimestampBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TimeStampViewHolder(binding)
            }

            ChatItemTypes.SEND_TEXT -> {
                val binding = ItemChatMessageSendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SendTextViewHolder(binding)
            }

            ChatItemTypes.RECEIVE_TEXT -> {
                val binding = ItemChatMessageReceiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceiveTextViewHolder(binding)
            }

            ChatItemTypes.SEND_IMAGE -> {
                val binding = ItemChatImageSendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SendImageViewHolder(binding)
            }

            ChatItemTypes.RECEIVE_IMAGE -> {
                val binding = ItemChatImageReceiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceiveImageViewHolder(binding)
            }

            ChatItemTypes.SEND_VIDEO -> {
                val binding = ItemChatVideoSendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SendVideoViewHolder(binding)
            }

            ChatItemTypes.RECEIVE_VIDEO -> {
                val binding = ItemChatVideoReceiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceiveVideoViewHolder(binding)
            }

            ChatItemTypes.SEND_LOCATION -> {
                val binding = ItemChatLocationSendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SendLocationViewHolder(binding)
            }

            ChatItemTypes.RECEIVE_LOCATION -> {
                val binding = ItemChatLocationReceiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceiveLocationViewHolder(binding)
            }

            ChatItemTypes.SEND_QUICK_PAY -> {
                val binding = ItemChatPaySendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SendPayViewHolder(binding)
            }

            ChatItemTypes.RECEIVE_QUICK_PAY -> {
                val binding = ItemChatPayReceiveBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReceivePayViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = getItem(position)
        when (holder.itemViewType) {
            ChatItemTypes.TIMESTAMP -> {
                val timestampViewHolder = holder as TimeStampViewHolder
                (message as? ChatTimestampDto)?.let {
                    timestampViewHolder.bind(it)
                }
            }

            ChatItemTypes.SEND_TEXT -> {
                val sendTextViewHolder = holder as SendTextViewHolder
                (message as? ChatTextDto)?.let {
                    sendTextViewHolder.bind(it)
                }
            }

            ChatItemTypes.RECEIVE_TEXT -> {
                val receiveTextViewHolder = holder as ReceiveTextViewHolder
                (message as? ChatTextDto)?.let {
                    receiveTextViewHolder.bind(it, onMessageLongClick)
                }
            }

            ChatItemTypes.SEND_IMAGE -> {
                val sendImageViewHolder = holder as SendImageViewHolder
                (message as? ChatImageDto)?.let {
                    sendImageViewHolder.bind(it)
                }
            }

            ChatItemTypes.RECEIVE_IMAGE -> {
                val receiveImageViewHolder = holder as ReceiveImageViewHolder
                (message as? ChatImageDto)?.let {
                    receiveImageViewHolder.bind(it, onMessageLongClick)
                }
            }

            ChatItemTypes.SEND_VIDEO -> {
                val sendVideoViewHolder = holder as SendVideoViewHolder
                (message as? ChatVideoDto)?.let {
                    sendVideoViewHolder.bind(it)
                }
            }

            ChatItemTypes.RECEIVE_VIDEO -> {
                val receiveVideoViewHolder = holder as ReceiveVideoViewHolder
                (message as? ChatVideoDto)?.let {
                    receiveVideoViewHolder.bind(it, onMessageLongClick)
                }
            }

            ChatItemTypes.SEND_LOCATION -> {
                val sendLocationViewHolder = holder as SendLocationViewHolder
                (message as? ChatLocationDto)?.let {
                    sendLocationViewHolder.bind(it, onMessageClick)
                }
            }

            ChatItemTypes.RECEIVE_LOCATION -> {
                val receiveLocationViewHolder = holder as ReceiveLocationViewHolder
                (message as? ChatLocationDto)?.let {
                    receiveLocationViewHolder.bind(it, onMessageLongClick, onMessageClick)
                }
            }

            ChatItemTypes.SEND_QUICK_PAY -> {
                val sendPayViewHolder = holder as SendPayViewHolder
                (message as? ChatPayDto)?.let {
                    sendPayViewHolder.bind(it)
                }
            }

            ChatItemTypes.RECEIVE_QUICK_PAY -> {
                val receivePayViewHolder = holder as ReceivePayViewHolder
                (message as? ChatPayDto)?.let {
                    receivePayViewHolder.bind(it, onMessageLongClick)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        when (message.chatDirection) {
            ChatDirection.SENT -> {
                return when (message.chatType) {
                    ChatType.TEXT -> ChatItemTypes.SEND_TEXT
                    ChatType.IMAGE -> ChatItemTypes.SEND_IMAGE
                    ChatType.AUDIO -> ChatItemTypes.SEND_AUDIO
                    ChatType.VIDEO -> ChatItemTypes.SEND_VIDEO
                    ChatType.LOCATION -> ChatItemTypes.SEND_LOCATION
                    ChatType.QUICK_PAY -> ChatItemTypes.SEND_QUICK_PAY
                    ChatType.POLL -> ChatItemTypes.SEND_POLL
                    ChatType.FILE -> ChatItemTypes.SEND_FILE
                    else -> -1
                }
            }

            ChatDirection.RECEIVED -> {
                return when (message.chatType) {
                    ChatType.TEXT -> ChatItemTypes.RECEIVE_TEXT
                    ChatType.IMAGE -> ChatItemTypes.RECEIVE_IMAGE
                    ChatType.AUDIO -> ChatItemTypes.RECEIVE_AUDIO
                    ChatType.VIDEO -> ChatItemTypes.RECEIVE_VIDEO
                    ChatType.LOCATION -> ChatItemTypes.RECEIVE_LOCATION
                    ChatType.QUICK_PAY -> ChatItemTypes.RECEIVE_QUICK_PAY
                    ChatType.POLL -> ChatItemTypes.RECEIVE_POLL
                    ChatType.FILE -> ChatItemTypes.RECEIVE_FILE
                    else -> -1
                }
            }

            ChatDirection.UNKNOWN -> {
                return when (message.chatType){
                    ChatType.TIMESTAMP -> ChatItemTypes.TIMESTAMP
                    else -> -1
                }
            }

                else -> return -1
        }
    }
}

class ChatDiffCallback : DiffUtil.ItemCallback<ChatDto>() {
    override fun areItemsTheSame(oldItem: ChatDto, newItem: ChatDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChatDto, newItem: ChatDto): Boolean {
        return oldItem.id == newItem.id
    }
}
