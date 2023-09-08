package com.business.swey.listingDetails

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.business.swey.R
import com.business.swey.databinding.FragmentPantsSizeFilterBinding
import com.business.swey.listingDetails.adapters.SizeTileFilterRecyclerViewAdapter
import com.business.swey.models.SizeTileFilterItemDTO
import com.business.swey.utils.Enum
import com.business.swey.utils.Utilities
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PantSizeFilterFragment: BottomSheetDialogFragment() {

    companion object Factory {
        fun create(): PantSizeFilterFragment = PantSizeFilterFragment()
    }

    private lateinit var fragmentViewBinding: FragmentPantsSizeFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentViewBinding = FragmentPantsSizeFilterBinding.inflate(inflater, container, false)
        fragmentViewBinding.root.setBackgroundColor(Color.TRANSPARENT)
        return fragmentViewBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = Utilities.instance.getHeightOfScreen()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentViewBinding.rvPantsLength.layoutManager = GridLayoutManager(this@PantSizeFilterFragment.context, 5, GridLayoutManager.VERTICAL, false)
        val adapter = SizeTileFilterRecyclerViewAdapter()
        adapter.setDataSource(createSizeDataSource(Enum.SizeType.normal))
        fragmentViewBinding.rvPantsLength.adapter = adapter
        //setupBlurredOverlayBackground()

        setupBlurredOverlayBackground()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupBlurredOverlayBackground() {
        val parent = view?.parent as? ViewGroup
        val overlayView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_overlay, parent, false)
        val blurredOverlay = createBlurredOverlay()

        // Set the blurred overlay as the view's background
        overlayView.background = BitmapDrawable(resources, blurredOverlay)

        // Set the overlay as the bottom sheet's background
        dialog?.window?.setBackgroundDrawable(overlayView.background)
    }

    private fun createBlurredOverlay(): Bitmap {
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.blue_overlay)
        val bitmap = Bitmap.createBitmap(drawable!!.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun createSizeDataSource(sizeType: Enum.SizeType): ArrayList<SizeTileFilterItemDTO> {
        var sizesList = ArrayList<SizeTileFilterItemDTO>()
        var initValue: Int = 35

        sizesList.add(SizeTileFilterItemDTO(Enum.SizeType.normal, "One\nSize", false))
        for (index in 0.. 17) {
            initValue += 1
            sizesList.add(SizeTileFilterItemDTO(sizeType, "$initValue\"", false))
        }
        sizesList.add(SizeTileFilterItemDTO(Enum.SizeType.normal, "Other", false))

        return sizesList
    }
}