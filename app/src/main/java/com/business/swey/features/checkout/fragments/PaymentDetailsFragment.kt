package com.business.swey.features.checkout.fragments

import android.animation.ObjectAnimator
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.business.swey.R
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.core.models.CreditCardDto
import com.business.swey.core.models.SweyUserDto
import com.business.swey.databinding.FragmentPaymentDetailsBinding
import com.business.swey.databinding.ViewPaymentDetailsBinding
import com.business.swey.databinding.ViewSweyWalletBinding
import com.business.swey.features.checkout.adapters.CreditCardAdapter
import com.business.swey.features.checkout.adapters.ProfilePictureAdapter

class PaymentDetailsFragment : FullScreenDialogBindingFragment<FragmentPaymentDetailsBinding>() {

    private val adapterPayees = ProfilePictureAdapter()

    private val adapterCards = CreditCardAdapter()

    private var currentViewType = CurrentViewType.PAYMENT

    private var isWalletChecked = false

    override fun getLayout() = R.layout.fragment_payment_details

    override fun initViews(binding: FragmentPaymentDetailsBinding) {
        adapterPayees.submitList(getPayeesDate())
        applyTextTransformations(binding.layoutPaymentDetails.tvTotalAmount, target = " $15 ")
        applyTextTransformations(binding.layoutChooseCard.tvTotalAmount, target = " $15 ")
        binding.layoutPaymentDetails.recyclerPictures.adapter = adapterPayees

        adapterCards.submitList(geCardsDate())
        binding.layoutChooseCard.recyclerViewCards.adapter = adapterCards
    }

    override fun setListeners(binding: FragmentPaymentDetailsBinding) {
        binding.ivBackArrow.setOnClickListener {
            when(currentViewType){
                CurrentViewType.PAYMENT -> dismiss()
                CurrentViewType.CARDS -> loadPaymentDetails(binding)
            }

        }

        binding.btnCheckoutBottom.setOnClickListener {
            when(currentViewType){
                CurrentViewType.PAYMENT -> openDialogSheet(
                    ReceiptSuccessfulFragment.getInstance(),
                    ReceiptSuccessfulFragment.TAG
                )//loadChooseCard(binding)
                CurrentViewType.CARDS -> openDialogSheet(
                    ReceiptSuccessfulFragment.getInstance(),
                    ReceiptSuccessfulFragment.TAG
                )
            }
        }

        setupTipListeners(binding.layoutPaymentDetails)

        setWalletListener(binding.layoutWallet)
    }

    private fun setupTipListeners(binding: ViewPaymentDetailsBinding) {
        val buttons = listOf(binding.tvTip0, binding.tvTip10, binding.tvTip25, binding.tvTip50)

        for (item in buttons) {
            item.setOnClickListener {
                for (tip in buttons)
                    if (tip == item) {
                        tip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        tip.background = ContextCompat.getDrawable(requireContext(), R.drawable.tip_background_selected)
                    } else {
                        tip.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_text_color_title))
                        tip.background = ContextCompat.getDrawable(requireContext(), R.drawable.tip_background_unselected)
                    }
            }
        }
    }

    private fun loadChooseCard(binding: FragmentPaymentDetailsBinding){
        val exitToLeftAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.exit_to_left)
        val enterFromRightAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.enter_from_right)
        currentViewType = CurrentViewType.CARDS
        binding.layoutChooseCard.root.isVisible = true
        binding.layoutChooseCard.root.animation = enterFromRightAnimation
        binding.layoutPaymentDetails.root.animation = exitToLeftAnimation
        enterFromRightAnimation.start()
        exitToLeftAnimation.start()
        exitToLeftAnimation.setAnimationListener(object  : AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.layoutPaymentDetails.root.isVisible = false
                binding.layoutWallet.root.isVisible = true
                binding.btnCheckoutBottom.text = "Proceed"
                binding.progressTop.progress = 65
                binding.tvTitle.text = "Payment"

            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    private fun loadPaymentDetails(binding: FragmentPaymentDetailsBinding){
        val enterFromLeftAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.enter_from_left)
        val exitToRightAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.exit_to_right)
        currentViewType = CurrentViewType.PAYMENT
        binding.layoutPaymentDetails.root.isVisible = true
        binding.layoutPaymentDetails.root.animation = enterFromLeftAnimation
        binding.layoutChooseCard.root.animation = exitToRightAnimation
        enterFromLeftAnimation.start()
        exitToRightAnimation.start()
        exitToRightAnimation.setAnimationListener(object  : AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.layoutChooseCard.root.isVisible = false
                binding.layoutWallet.root.isVisible = false
                binding.btnCheckoutBottom.text = "Choose Card"
                binding.progressTop.progress = 50
                binding.tvTitle.text = "Cart"
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    private fun setWalletListener(binding: ViewSweyWalletBinding){
        var progress = 0
        binding.root.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            isWalletChecked = !isWalletChecked
            if (isWalletChecked)
                binding.ivCheck.setImageResource(R.drawable.ic_filled_mark)
            else
                binding.ivCheck.setImageResource(R.drawable.ic_unfilled_mark)
        }
        binding.root.setOnLongClickListener {
            progress += 20
            progress %= 100
            ObjectAnimator.ofInt(binding.progressWalletCoin, "progress", progress)
                .setDuration(500)
                .start()
            binding.ivCheck.setImageResource(
                if (progress >= 10)
                    R.drawable.ic_filled_mark_white else R.drawable.ic_filled_mark
            )
            binding.tvTitle.setTextColor(
                ContextCompat.getColor(requireContext(), if (progress >= 50) R.color.white else R.color.primary_text_color_title)
            )
            true
        }
    }

    private fun applyTextTransformations(textView: TextView, target: String){
        val spannable = SpannableString(textView.text)
        val startIndex = textView.text.indexOf(target)
        val endIndex = startIndex + target.length
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.appPrimaryColor))
        spannable.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannable
    }

    private fun getPayeesDate(): List<SweyUserDto>{
        return listOf(
            SweyUserDto(id = 1, imageRes = R.drawable.ic_person_2),
            SweyUserDto(id = 2, imageRes = R.drawable.ic_person_3),
            SweyUserDto(id = 3, imageRes = R.drawable.ic_person_4),
        )
    }

    private fun geCardsDate(): List<CreditCardDto>{
        return listOf(
            CreditCardDto(id = 1, network = "VISA", lastFourDigits = 1234, amount = 150),
            CreditCardDto(id = 2, network = "MC", lastFourDigits = 5678, amount = 2000),
            CreditCardDto(id = 3, network = "AMEX", lastFourDigits = 9101, amount = 3500)
        )
    }

    companion object {
        const val TAG = "PaymentDetailsFragment"

        fun getInstance() = PaymentDetailsFragment()
    }

    enum class CurrentViewType{
        PAYMENT,
        CARDS
    }
}
