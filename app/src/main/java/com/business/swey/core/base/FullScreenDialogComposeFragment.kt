package com.business.swey.core.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.business.swey.R
import com.google.android.material.elevation.SurfaceColors

abstract class FullScreenDialogComposeFragment(
    private val modifier: Modifier = Modifier,
) : DialogFragment() {

    @Composable
    abstract fun GetContent()

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
    ): View {
        return ComposeView(requireContext()).apply {
            updateSystemColors()
            setContent {
                Surface(
                    modifier = modifier.fillMaxSize(),
                    color = colorResource(id = R.color.primary_background_color)
                ) {
                    GetContent()
                }
            }
        }
    }

    private fun updateSystemColors() {
        dialog?.window?.navigationBarColor = SurfaceColors.SURFACE_0.getColor(requireContext())
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

    fun openDialogSheet(
        sheet: FullScreenDialogComposeFragment,
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
