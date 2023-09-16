package com.business.swey.features.home.chat.adapters.viewHolders

import android.net.Uri
import android.view.View
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.core.models.ChatDto
import com.business.swey.core.models.ChatImageDto
import com.business.swey.core.models.ChatLocationDto
import com.business.swey.core.models.ChatPayDto
import com.business.swey.core.models.ChatTextDto
import com.business.swey.core.models.ChatTimestampDto
import com.business.swey.core.models.ChatVideoDto
import com.business.swey.core.models.PaymentDirection
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


class TimeStampViewHolder(private val binding: ItemChatTimestampBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatTimestampDto) {
        binding.timestamp = message
    }
}

class SendTextViewHolder(private val binding: ItemChatMessageSendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatTextDto) {
        binding.message = message
        binding.layoutReply.root.isVisible = message.repliedTo != null
    }
}

class ReceiveTextViewHolder(private val binding: ItemChatMessageReceiveBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatTextDto, onMessageLongClick: (ChatTextDto, IntArray) -> Unit) {

        binding.message = message

        binding.layoutReply.root.isVisible = message.repliedTo != null

        binding.tvMessageSend.setOnLongClickListener {
            val location = IntArray(2)
            binding.root.getLocationOnScreen(location)
            onMessageLongClick.invoke(message, location)
            true
        }
    }
}

class SendImageViewHolder(private val binding: ItemChatImageSendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatImageDto) {

        binding.message = message
    }
}

class ReceiveImageViewHolder(private val binding: ItemChatImageReceiveBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatImageDto, onMessageLongClick: (ChatDto, IntArray) -> Unit) {

        binding.message = message

        binding.ivMessageSend.setOnLongClickListener {
            val location = IntArray(2)
            binding.root.getLocationOnScreen(location)
            onMessageLongClick.invoke(message, location)
            true
        }
    }
}

class SendVideoViewHolder(private val binding: ItemChatVideoSendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val videoView = binding.videoView
    private val btnPlayPause = binding.btnPlayPause

    fun bind(message: ChatVideoDto) {
        binding.message = message

        resetVideoView(videoView, btnPlayPause)
        val videoUri = Uri.parse(message.videoUri)

        videoView.setVideoURI(videoUri)

        btnPlayPause.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
                binding.ivMessageSend.elevation = 0f
                videoView.isVisible = true
                btnPlayPause.isVisible = false
            }
        }

        videoView.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                btnPlayPause.isVisible = true
            }
        }

        videoView.setOnCompletionListener {
            btnPlayPause.isVisible = true
            videoView.isVisible = false
            binding.ivMessageSend.elevation = 2f
        }
    }
}

class ReceiveVideoViewHolder(private val binding: ItemChatVideoReceiveBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val videoView = binding.videoView
    private val btnPlayPause = binding.btnPlayPause

    fun bind(message: ChatVideoDto, onMessageLongClick: (ChatDto, IntArray) -> Unit) {
        binding.message = message

//        binding.ivMessageSend.setOnLongClickListener {
//            val location = IntArray(2)
//            binding.root.getLocationOnScreen(location)
//            onMessageLongClick.invoke(message, location)
//            true
//        }

        resetVideoView(videoView, btnPlayPause)
        val videoUri = Uri.parse(message.videoUri)

        videoView.setVideoURI(videoUri)

        btnPlayPause.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
                binding.ivMessageSend.elevation = 0f
                videoView.isVisible = true
                btnPlayPause.isVisible = false
            }
        }

        videoView.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                btnPlayPause.isVisible = true
            }
        }

        videoView.setOnCompletionListener {
            btnPlayPause.isVisible = true
            videoView.isVisible = false
            binding.ivMessageSend.elevation = 2f
        }
    }
}

class SendLocationViewHolder(private val binding: ItemChatLocationSendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatLocationDto, onClick: (ChatDto)-> Unit) {

        binding.message = message

        binding.ivMessageSend.setOnClickListener{
            onClick.invoke(message)
        }
    }
}

class ReceiveLocationViewHolder(private val binding: ItemChatLocationReceiveBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatLocationDto, onMessageLongClick: (ChatDto, IntArray) -> Unit, onClick: (ChatDto)-> Unit) {

        binding.message = message

        binding.ivMessageSend.setOnLongClickListener {
            val location = IntArray(2)
            binding.root.getLocationOnScreen(location)
            onMessageLongClick.invoke(message, location)
            true
        }

        binding.ivMessageSend.setOnClickListener{
            onClick.invoke(message)
        }
    }
}


private fun resetVideoView(videoView: VideoView, button: View){
    button.isVisible = true
    videoView.elevation = 0F
    videoView.isVisible = false
    videoView.suspend()
    videoView.stopPlayback()
}

class SendPayViewHolder(private val binding: ItemChatPaySendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatPayDto) {
        binding.message = message
        if (message.paymentDirection == PaymentDirection.REQUEST)
            binding.layoutPayment.tvButton.text = "Cancel"

    }
}

class ReceivePayViewHolder(private val binding: ItemChatPayReceiveBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: ChatPayDto, onMessageLongClick: (ChatDto, IntArray) -> Unit) {

        binding.message = message

        if (message.paymentDirection == PaymentDirection.REQUEST)
            binding.layoutPayment.tvButton.text = "Pay"

        binding.cardParent.setOnLongClickListener {
            val location = IntArray(2)
            binding.root.getLocationOnScreen(location)
            onMessageLongClick.invoke(message, location)
            true
        }
    }
}