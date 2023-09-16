package com.business.swey.features.listingDetails

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.business.swey.R
import com.business.swey.databinding.FragmentSizeTilesFilterBinding
import com.business.swey.features.listingDetails.adapters.SizeTileFilterRecyclerViewAdapter
import com.business.swey.core.models.SizeTileFilterItemDTO
import com.business.swey.core.utils.Enum
import com.business.swey.core.utils.Enum.SizeType.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout

class SizeTileFilterSheetFragment: BottomSheetDialogFragment() {

    companion object Factory {
        fun create(): SizeTileFilterSheetFragment = SizeTileFilterSheetFragment()
    }

    private lateinit var fragmentViewBinding: FragmentSizeTilesFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentViewBinding = FragmentSizeTilesFilterBinding.inflate(inflater, container, false)
        return fragmentViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragmentViewBinding.tlSizeTileType.addTab(fragmentViewBinding.tlSizeTileType.newTab().setText("EU"))
        fragmentViewBinding.tlSizeTileType.addTab(fragmentViewBinding.tlSizeTileType.newTab().setText("US"))
        fragmentViewBinding.tlSizeTileType.addTab(fragmentViewBinding.tlSizeTileType.newTab().setText("UK"))

        fragmentViewBinding.rvSizeTile.layoutManager = GridLayoutManager(this@SizeTileFilterSheetFragment.context, 5, GridLayoutManager.VERTICAL, false)
        val adapter = SizeTileFilterRecyclerViewAdapter()
        adapter.setDataSource(createSizeDataSource(eu))
        fragmentViewBinding.rvSizeTile.adapter = adapter
        setupBlurredOverlayBackground()

        fragmentViewBinding.tlSizeTileType.addOnTabSelectedListener(object:
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (fragmentViewBinding.tlSizeTileType.selectedTabPosition == 0) {
                    adapter.setDataSource(createSizeDataSource(eu))
                }
                else if (fragmentViewBinding.tlSizeTileType.selectedTabPosition == 1) {
                    adapter.setDataSource(createSizeDataSource(us))
                }
                else {
                    adapter.setDataSource(createSizeDataSource(uk))
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupBlurredOverlayBackground() {

//        val parent = view?.parent as? ViewGroup
//        val overlayView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_overlay, parent, false)
//        val blurredOverlay = createBlurredOverlay()
//
//        // Set the blurred overlay as the view's background
//        overlayView.background = BitmapDrawable(resources, blurredOverlay)
//
//        // Set the overlay as the bottom sheet's background
//        dialog?.window?.setBackgroundDrawable(overlayView.background)
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
        var initValue: Double = when (sizeType) {
            uk -> {
                 6.5
            }
            us -> {
                5.5
            }
            eu -> {
                35.5
            }
            normal -> {
                24.0
            }
        }

        sizesList.add(SizeTileFilterItemDTO(normal, "One\nSize", false))
        for (index in 0.. 17) {
            initValue += 0.5
            sizesList.add(SizeTileFilterItemDTO(sizeType, "${initValue}", false))
        }
        sizesList.add(SizeTileFilterItemDTO(normal, "Other", false))

        return sizesList
    }

}