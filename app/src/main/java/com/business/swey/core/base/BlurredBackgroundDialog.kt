package com.business.swey.core.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.business.swey.R
import jp.wasabeef.blurry.Blurry

abstract class BlurredBackgroundDialog<DB : ViewDataBinding> : FullScreenDialogBindingFragment<DB>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
    }

    override fun initViews(binding: DB) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.attributes?.windowAnimations = R.style.FullScreenDialogStyleFade
        handleBlur(binding)
    }

    override fun setListeners(binding: DB) {
        binding.root.setOnClickListener {
            dismiss()
        }
    }

    private fun handleBlur(binding: DB) {
        binding.root.post {
            val rootView = parentFragment?.view?.rootView

            if (rootView != null && rootView.width > 0 && rootView.height > 0) {
                Blurry.with(requireContext())
                    .radius(20)
                    .sampling(2)
                    .capture(rootView)
                    .getAsync { blurredBitmap ->
                        if (blurredBitmap != null) {
                            binding.root.findViewById<ImageView>(R.id.ivBackground).setImageBitmap(blurredBitmap)
                        }
                    }
            }
        }
    }
}