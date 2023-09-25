package com.business.swey.core.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.business.swey.R
import com.google.android.material.elevation.SurfaceColors


abstract class FullScreenDialogBindingFragment<DB : ViewDataBinding>() :
    DialogFragment() {

    lateinit var _binding: DB

    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun initViews(binding: DB)
    abstract fun setListeners(binding: DB)
    open fun setObservers() {}

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        dialog.window?.attributes?.windowAnimations =
            R.style.FullScreenDialogStyleEnterFromRightAndExitToRight
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_TITLE,
            android.R.style.Theme_Material_Light_NoActionBar
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayout(), null, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primary_background_color))
        dialog?.window?.navigationBarColor = SurfaceColors.SURFACE_0.getColor(requireContext())
        updateStatusBar()
        initViews(_binding)
        setListeners(_binding)
        setObservers()
    }

    private fun updateStatusBar() {
        dialog?.window?.statusBarColor = SurfaceColors.SURFACE_0.getColor(requireContext())
    }

    fun <DB : ViewDataBinding> openDialogSheet(
        sheet: FullScreenDialogBindingFragment<DB>,
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
