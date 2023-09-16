package com.business.swey.features.home.chat.fragments

import android.widget.Toast
import com.business.swey.R
import com.business.swey.core.base.BlurredBackgroundDialog
import com.business.swey.databinding.DialogChatMenuBinding

class ChatOptionsDialog : BlurredBackgroundDialog<DialogChatMenuBinding>() {

    override fun getLayout() = R.layout.dialog_chat_menu

    override fun setListeners(binding: DialogChatMenuBinding) {
        binding.root.setOnClickListener {
            dismiss()
        }
        binding.btnQuickPay.setOnClickListener {
            openDialogSheet(MoneyExchangeDialog.getInstance(moneyTransferType = MoneyExchangeDialog.MoneyTransferType.SEND), MoneyExchangeDialog.TAG)
        }
        binding.btnNewChat.setOnClickListener {
            Toast.makeText(requireContext(), "New Chat", Toast.LENGTH_SHORT).show()
        }
        binding.btnNewGroup.setOnClickListener {
            Toast.makeText(requireContext(), "New Group", Toast.LENGTH_SHORT).show()
        }
        binding.btnCross.setOnClickListener {
            dismiss()
        }
    }

    companion object{
        const val TAG = "ChatOptionsPopUpDialog"

        fun getInstance() : ChatOptionsDialog = ChatOptionsDialog()
    }
}