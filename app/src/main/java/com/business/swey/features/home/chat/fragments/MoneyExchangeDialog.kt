package com.business.swey.features.home.chat.fragments

import android.os.Bundle
import android.widget.Toast
import com.business.swey.R
import com.business.swey.core.base.BlurredBackgroundDialog
import com.business.swey.databinding.DialogMoneyExchangeBinding

class MoneyExchangeDialog : BlurredBackgroundDialog<DialogMoneyExchangeBinding>() {

    private var dialogType = MoneyTransferType.SEND
    override fun getLayout() = R.layout.dialog_money_exchange

    override fun initViews(binding: DialogMoneyExchangeBinding) {
        super.initViews(binding)
        (arguments?.getSerializable(TYPE) as? MoneyTransferType)?.let {
            dialogType =  it
        }
        if (dialogType == MoneyTransferType.REQUEST){
            binding.tvTitle.text = "Request Money"
        }
    }

    override fun setListeners(binding: DialogMoneyExchangeBinding) {
        super.setListeners(binding)
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        binding.btnTransfer.setOnClickListener {
            Toast.makeText(requireContext(), "Transferred", Toast.LENGTH_SHORT).show()
            dismissAllowingStateLoss()
        }
    }

    companion object {
        const val TAG = "MoneyExchangeDialog"

        fun getInstance(moneyTransferType: MoneyTransferType): MoneyExchangeDialog =
            MoneyExchangeDialog().apply {
                val bundle = Bundle()
                bundle.putSerializable(TYPE, moneyTransferType)
                arguments = bundle
            }

        const val TYPE = "TYPE"
    }

    enum class MoneyTransferType {
        SEND,
        REQUEST
    }
}