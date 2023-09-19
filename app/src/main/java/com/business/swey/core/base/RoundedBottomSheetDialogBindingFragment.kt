package com.business.swey.core.base

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.business.swey.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class RoundedBottomSheetDialogBindingFragment<DB : ViewDataBinding>(
    private val fullHeight: Boolean = false,
    private val adjustHeight: Boolean = true,
    private val isDraggable: Boolean = true
) :
    BottomSheetDialogFragment() {

    lateinit var _binding: DB

    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun initViews(binding: DB)
    open fun setListeners(binding: DB) {}
    open fun setObservers() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayout(), null, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutPrams()
        setCornersRound()
        initViews(_binding)
        setListeners(_binding)
        setObservers()
    }

    open fun setLayoutPrams() {
        val layoutParams =
            (_binding.root.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior is BottomSheetBehavior<*>) {
            if (fullHeight)
                layoutParams.height =
                    Resources.getSystem().displayMetrics.heightPixels - if (adjustHeight) 100 else 0
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = isDraggable
        }
    }

    open fun setCornersRound() {
        when (_binding.root.background) {
            null -> {
                _binding.root.apply {
                    val unwrappedDrawable = AppCompatResources.getDrawable(
                        this.context,
                        R.drawable.sheet_background_default
                    )
                    unwrappedDrawable?.let {
                        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)
                        val colorInt = ContextCompat.getColor(this.context, R.color.primary_background_color)
                        val hex = Integer.toHexString(colorInt)
                        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#$hex"))
                        background = ContextCompat.getDrawable(this.context, R.drawable.sheet_background_default)
                    }
                }
            }

            is ColorDrawable -> {
                _binding.root.apply {
                    val unwrappedDrawable = AppCompatResources.getDrawable(
                        this.context,
                        R.drawable.sheet_background_default
                    )
                    unwrappedDrawable?.let {
                        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable)
                        val colorInt = (this.background as ColorDrawable).color
                        val hex = Integer.toHexString(colorInt)
                        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#$hex"))
                        background =
                            ContextCompat.getDrawable(context, R.drawable.sheet_background_default)
                    }
                }
            }
        }
    }

    fun <DB : ViewDataBinding> openBottomSheet(
        sheet: RoundedBottomSheetDialogBindingFragment<DB>,
        tag: String,
        keepCurrentAlive: Boolean = true
    ) {
        if (keepCurrentAlive)
            sheet.show(childFragmentManager, tag)
        else {
            dismissAllowingStateLoss()
            sheet.show(parentFragmentManager, tag)
        }
    }
}