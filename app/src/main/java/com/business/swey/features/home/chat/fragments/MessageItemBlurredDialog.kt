package com.business.swey.features.home.chat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.business.swey.R
import com.business.swey.core.base.BlurredBackgroundDialog
import com.business.swey.core.models.ChatDto
import com.business.swey.core.models.ChatImageDto
import com.business.swey.core.models.ChatLocationDto
import com.business.swey.core.models.ChatPayDto
import com.business.swey.core.models.ChatTextDto
import com.business.swey.databinding.DialogMessageItemBlurredBinding
import com.business.swey.databinding.ItemChatImageReceiveBinding
import com.business.swey.databinding.ItemChatLocationReceiveBinding
import com.business.swey.databinding.ItemChatMessageReceiveBinding
import com.business.swey.databinding.ItemChatPayReceiveBinding

class MessageItemBlurredDialog :
    BlurredBackgroundDialog<DialogMessageItemBlurredBinding>() {
    override fun getLayout() = R.layout.dialog_message_item_blurred

    override fun initViews(binding: DialogMessageItemBlurredBinding) {
        super.initViews(binding)

        (arguments?.getSerializable(COORDINATES) as? IntArray)?.let { location ->
            (arguments?.getSerializable(CHAT) as? ChatTextDto)?.let { chat ->
                drawMessage(binding, location, chat)
            }
            (arguments?.getSerializable(CHAT) as? ChatDto)?.let { chat ->
                drawMessage(binding, location, chat)
            }
        }
    }

    private fun drawMessage(
        binding: DialogMessageItemBlurredBinding,
        location: IntArray,
        chatData: ChatDto
    ) {
        val screenX = location[0]
        val screenY = location[1] - resources.getDimension(com.intuit.sdp.R.dimen._20sdp).toInt()

        val itemBinding = when (chatData) {
            is ChatTextDto -> {
                ItemChatMessageReceiveBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.parent,
                    false
                ).apply {
                    message = chatData
                }

            }
            is ChatImageDto -> {
                ItemChatImageReceiveBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.parent,
                    false
                ).apply {
                    message = chatData
                }
            }
            is ChatLocationDto -> {
                ItemChatLocationReceiveBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.parent,
                    false
                ).apply {
                    message = chatData
                }
            }
            is ChatPayDto -> {
                ItemChatPayReceiveBinding.inflate(
                    LayoutInflater.from(binding.root.context),
                    binding.parent,
                    false
                ).apply {
                    message = chatData
                }
            }
            else -> return
        }

        // Add the inflated layout to the parent layout
        val layout: FrameLayout.LayoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                topMargin = screenY
                leftMargin = screenX
            }

        binding.parent.addView(itemBinding.root, layout)
    }

    companion object {
        const val TAG = "MessageItemBlurredDialog"

        fun getInstance(
            chatData: ChatDto,
            location: IntArray
        ): MessageItemBlurredDialog = MessageItemBlurredDialog().apply {
            val bundle = Bundle()
            bundle.putSerializable(CHAT, chatData)
            bundle.putSerializable(COORDINATES, location)
            arguments = bundle
        }

        const val CHAT = "chat"
        const val COORDINATES = "coords"
    }
}