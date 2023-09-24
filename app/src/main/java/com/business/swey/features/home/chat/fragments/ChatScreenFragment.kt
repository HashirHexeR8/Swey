package com.business.swey.features.home.chat.fragments

import android.graphics.Color
import android.text.Editable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.models.ChatTextDto
import com.business.swey.core.models.ChatDirection
import com.business.swey.core.models.ChatDto
import com.business.swey.core.models.ChatImageDto
import com.business.swey.core.models.ChatLocationDto
import com.business.swey.core.models.ChatPayDto
import com.business.swey.core.models.ChatStatusType
import com.business.swey.core.models.ChatTimestampDto
import com.business.swey.core.models.ChatType
import com.business.swey.core.models.ChatVideoDto
import com.business.swey.core.models.PaymentDirection
import com.business.swey.core.utils.VibrateUtil
import com.business.swey.core.utils.runDelayed
import com.business.swey.core.views.SwipeHelper
import com.business.swey.databinding.FragmentChatScreenBinding
import com.business.swey.features.home.chat.adapters.ChatMessagesAdapter
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date


class ChatScreenFragment : FullScreenDialogBindingFragment<FragmentChatScreenBinding>() {

    private val adapter: ChatMessagesAdapter = ChatMessagesAdapter(
        onMessageLongClick = ::onMessageLongClicked,
        onMessageClick = ::onMessageClicked
    )

    override fun getLayout() = R.layout.fragment_chat_screen

    override fun initViews(binding: FragmentChatScreenBinding) {
        adapter.submitList(getDate())
        binding.recyclerViewChat.adapter = adapter
        binding.recyclerViewChat.scrollToPosition(adapter.itemCount - 1)

        val itemTouchHelper = ItemTouchHelper(
            SwipeHelper(
                mContext = requireContext(),
                thresholdX = 200F
            ) { position -> setupReplySection(binding, position) })
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewChat)
    }

    override fun setListeners(binding: FragmentChatScreenBinding) {
        setupAttachmentListeners(binding)

        binding.ibBackButton.setOnClickListener {
            dismiss()
        }

        binding.layoutChatEdittext.editTextMessage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.recyclerViewChat.postDelayed({
                    binding.recyclerViewChat.scrollToPosition(adapter.itemCount - 1)
                }, 200)
            }
        }

        binding.layoutChatEdittext.editTextMessage.addTextChangedListener {
            if (!it.isNullOrEmpty() && it.isNotBlank())
                showSendButton(binding)
            else {
                binding.layoutChatEdittext.editTextMessage.clearFocus()
                hideSendButton(binding)
            }
        }

        binding.layoutChatEdittext.btnSend.setOnClickListener {
            VibrateUtil.vibrateWithEffect(requireContext(), 100)
            val message = binding.layoutChatEdittext.editTextMessage.text
            binding.layoutChatEdittext.editTextMessage.text =
                Editable.Factory.getInstance().newEditable("")
            adapter.submitList(
                adapter.currentList + ChatTextDto(
                    id = adapter.currentList.size + 1,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.SENT,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = message.toString()
                ),
            )
            binding.recyclerViewChat.postDelayed({
                binding.recyclerViewChat.scrollToPosition(adapter.itemCount - 1)
            }, 200)
        }

        binding.layoutChatEdittext.btnRecord.setOnClickListener {
            Toast.makeText(requireContext(), "Recording", Toast.LENGTH_SHORT).show()
        }

        binding.layoutChatEdittext.btnAttach.setOnClickListener {
            val attachmentLayout = binding.layoutChatEdittext.layoutAttachment.root
            if (!attachmentLayout.isVisible) {
                attachmentLayout.scaleY = 0.2f
                attachmentLayout.alpha = 0.2f
                attachmentLayout.isVisible = true
                attachmentLayout.animate()
                    .scaleY(1f)
                    .alpha(1f)
                    .start()
            }
        }

        binding.btnChatSettings.setOnClickListener {
            Toast.makeText(requireContext(), "Chat Settings", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupReplySection(binding: FragmentChatScreenBinding, position: Int) {
        VibrateUtil.vibrateWithEffect(requireContext(), 100)
        binding.layoutChatEdittext.layoutReply.isVisible = true
        binding.layoutChatEdittext.btnAttach.isVisible = false
        binding.layoutChatEdittext.separatorLineStart.isVisible = false
        binding.layoutChatEdittext.tvDescription.text = (adapter.currentList[position] as? ChatTextDto)?.message
    }

    private fun setupAttachmentListeners(binding: FragmentChatScreenBinding) {
        val attachmentBinding = binding.layoutChatEdittext.layoutAttachment
        attachmentBinding.layoutCancel.setOnClickListener {
            binding.layoutChatEdittext.layoutAttachment.root.isVisible = false
        }

        binding.layoutChatEdittext.btnCloseReply.setOnClickListener {
            binding.layoutChatEdittext.layoutReply.isVisible = false
            binding.layoutChatEdittext.btnAttach.isVisible = true
            binding.layoutChatEdittext.separatorLineStart.isVisible = true
        }

        attachmentBinding.btnPay.setOnClickListener {
            attachmentBinding.parentAttachments.isVisible = false
            attachmentBinding.layoutTransferMoney.isVisible = true
        }

        attachmentBinding.layoutSendMoney.setOnClickListener {
            //Select Send
            attachmentBinding.layoutSendMoney.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.money_transfer_bg_gradient)
            attachmentBinding.ivSend.setImageResource(R.drawable.ic_filled_mark_green)
            attachmentBinding.tvSend.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )

            //Un-Select Request
            attachmentBinding.layoutRequestMoney.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.money_transfer_bg_border_only
            )
            attachmentBinding.ivRequest.setImageResource(R.drawable.ic_unfilled_mark)
            attachmentBinding.tvRequest.setTextColor(Color.parseColor("#2E3A59"))

            runDelayed(500, lifecycleScope) {
                attachmentBinding.parentAttachments.isVisible = true
                attachmentBinding.layoutTransferMoney.isVisible = false
                openDialogSheet(
                    MoneyExchangeDialog.getInstance(moneyTransferType = MoneyExchangeDialog.MoneyTransferType.SEND),
                    MoneyExchangeDialog.TAG
                )
            }
        }

        attachmentBinding.layoutRequestMoney.setOnClickListener {
            //Select Send
            attachmentBinding.layoutRequestMoney.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.money_transfer_bg_gradient)
            attachmentBinding.ivRequest.setImageResource(R.drawable.ic_filled_mark_green)
            attachmentBinding.tvRequest.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )

            //Un-Select Request
            attachmentBinding.layoutSendMoney.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.money_transfer_bg_border_only
            )
            attachmentBinding.ivSend.setImageResource(R.drawable.ic_unfilled_mark)
            attachmentBinding.tvSend.setTextColor(Color.parseColor("#2E3A59"))

            runDelayed(500, lifecycleScope) {
                attachmentBinding.parentAttachments.isVisible = true
                attachmentBinding.layoutTransferMoney.isVisible = false
                openDialogSheet(
                    MoneyExchangeDialog.getInstance(moneyTransferType = MoneyExchangeDialog.MoneyTransferType.REQUEST),
                    MoneyExchangeDialog.TAG
                )
            }
        }
    }

    private fun showSendButton(binding: FragmentChatScreenBinding) {
        binding.layoutChatEdittext.apply {
            btnSend.isVisible = true
            btnRecord.isVisible = false
            separatorLineEnd.isVisible = false
        }
    }

    private fun hideSendButton(binding: FragmentChatScreenBinding) {
        binding.layoutChatEdittext.apply {
            btnSend.isVisible = false
            btnRecord.isVisible = true
            separatorLineEnd.isVisible = true
        }
    }

    private fun onMessageLongClicked(chatData: ChatDto, location: IntArray) {
        openDialogSheet(
            MessageItemBlurredDialog.getInstance(chatData, location),
            MessageItemBlurredDialog.TAG
        )
    }

    private fun onMessageClicked(chatDto: ChatDto) {
        Toast.makeText(requireContext(), "Open Google Maps!", Toast.LENGTH_SHORT).show()
    }

    private fun getDate(): List<ChatDto> {
        val messages = listOf(
            ChatTimestampDto(
                id = 1,
                timestamp = "Today",
                chatType = ChatType.TIMESTAMP,
                chatDirection = ChatDirection.UNKNOWN,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES))
            ),
            ChatTextDto(
                id = 2,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now()),
                message = "Hey there! How's it going?"
            ),
            ChatTextDto(
                id = 3,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)),
                message = "Hi! I'm doing well, thanks for asking. 😊"
            ),
            ChatTimestampDto(
                id = 4,
                timestamp = "12:36 AM",
                chatType = ChatType.TIMESTAMP,
                chatDirection = ChatDirection.UNKNOWN,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES))
            ),
            ChatImageDto(
                id = 5,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.IMAGE,
                time = Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)),
                imageSize = "153KB",
                imageUrl = "https://images.unsplash.com/photo-1481349518771-20055b2a7b24?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cmFuZG9tfGVufDB8fDB8fHww&w=1000&q=80"
            ),
            ChatTextDto(
                id = 6,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(2, ChronoUnit.MINUTES)),
                message = "That's great to hear! Any exciting plans for the weekend?"
            ),
            ChatTextDto(
                id = 7,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(3, ChronoUnit.MINUTES)),
                message = "Not sure yet. Maybe a hiking trip if the weather's nice. 🌞"
            ),
            ChatTextDto(
                id = 8,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(4, ChronoUnit.MINUTES)),
                message = "Hiking sounds like a fantastic idea! Enjoy the outdoors. 🏞️"
            ),
            ChatTimestampDto(
                id = 9,
                timestamp = "2:35 PM",
                chatType = ChatType.TIMESTAMP,
                chatDirection = ChatDirection.UNKNOWN,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES))
            ),
            ChatImageDto(
                id = 10,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.IMAGE,
                time = Date.from(Instant.now().plus(4, ChronoUnit.MINUTES)),
                imageSize = "94KB",
                imageUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fHww&w=1000&q=80️"
            ),
            ChatTextDto(
                id = 11,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)),
                message = "Thanks! I'll definitely make the most of it. 😁"
            ),
            ChatTextDto(
                id = 12,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(6, ChronoUnit.MINUTES)),
                message = "Let me know if you need any hiking tips or recommendations!"
            ),
            ChatTextDto(
                id = 13,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(7, ChronoUnit.MINUTES)),
                message = "I'll keep that in mind. Thanks for offering! 🥾"
            ),
            ChatTextDto(
                id = 14,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(8, ChronoUnit.MINUTES)),
                message = "No problem! Have a fantastic time hiking."
            ),
            ChatTextDto(
                id = 15,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES)),
                message = "Thank you! Catch up with you later."
            ),
            ChatTimestampDto(
                id = 16,
                timestamp = "6:00 PM",
                chatType = ChatType.TIMESTAMP,
                chatDirection = ChatDirection.UNKNOWN,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES))
            ),
            ChatVideoDto(
                id = 17,
                messageStatus = ChatStatusType.DELIVERED,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.VIDEO,
                time = Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)),
                videoSize = "35MB",
                thumbnailUrl = "https://marketplace.canva.com/EAEqfS4X0Xw/1/0/1600w/canva-most-attractive-youtube-thumbnail-wK95f3XNRaM.jpg",
                videoUri = "android.resource://${activity?.packageName}/${R.raw.sample_1}"
            ),
            ChatVideoDto(
                id = 18,
                messageStatus = ChatStatusType.UNKNOWN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.VIDEO,
                time = Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)),
                videoSize = "20MB",
                thumbnailUrl = "https://marketplace.canva.com/EAFf5rfnPgA/1/0/1600w/canva-blue-modern-eye-catching-vlog-youtube-thumbnail-LEcp-BYepDU.jpg",
                videoUri = "android.resource://${activity?.packageName}/${R.raw.sample_2}"
            ),
            ChatTextDto(
                id = 19,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.TEXT,
                time = Date.from(Instant.now().plus(7, ChronoUnit.MINUTES)),
                message = "Here's my pin location!!!😁"
            ),
            ChatLocationDto(
                id = 20,
                messageStatus = ChatStatusType.UNKNOWN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.LOCATION,
                time = Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)),
                imagePath = "https://www.mkiwi.com/maps/Google%20Map%20Limits/Google%20Static%20Maps%20API%20-%20Google%20Code_files/staticmap.png"
            ),
            ChatLocationDto(
                id = 21,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.LOCATION,
                time = Date.from(Instant.now().plus(16, ChronoUnit.MINUTES)),
                imagePath = "https://www.mkiwi.com/maps/Google%20Map%20Limits/Google%20Static%20Maps%20API%20-%20Google%20Code_files/staticmap.png"
            ),
            ChatTimestampDto(
                id = 22,
                timestamp = "10:15 PM",
                chatType = ChatType.TIMESTAMP,
                chatDirection = ChatDirection.UNKNOWN,
                time = Date.from(Instant.now().plus(9, ChronoUnit.MINUTES))
            ),
            ChatPayDto(
                id = 23,
                messageStatus = ChatStatusType.UNKNOWN,
                chatDirection = ChatDirection.RECEIVED,
                chatType = ChatType.QUICK_PAY,
                time = Date.from(Instant.now().plus(16, ChronoUnit.MINUTES)),
                paymentDirection = PaymentDirection.REQUEST,
                amount = 9.25
            ),
            ChatPayDto(
                id = 24,
                messageStatus = ChatStatusType.SEEN,
                chatDirection = ChatDirection.SENT,
                chatType = ChatType.QUICK_PAY,
                time = Date.from(Instant.now().plus(16, ChronoUnit.MINUTES)),
                paymentDirection = PaymentDirection.REQUEST,
                amount = 24.75
            )
        )
        messages[2].repliedTo = messages[1] as? ChatTextDto
        messages[8].repliedTo = messages[7] as? ChatTextDto
        messages[13].repliedTo = messages[12] as? ChatTextDto
        return messages
    }

    companion object {
        const val TAG = "ChatScreenFragment"

        fun getInstance(): ChatScreenFragment = ChatScreenFragment()
    }
}