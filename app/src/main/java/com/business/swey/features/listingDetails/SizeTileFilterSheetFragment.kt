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
import com.business.swey.core.base.RoundedBottomSheetDialogBindingFragment
import com.business.swey.databinding.FragmentSizeTilesFilterBinding
import com.business.swey.features.listingDetails.adapters.SizeTileFilterRecyclerViewAdapter
import com.business.swey.core.models.SizeTileFilterItemDTO
import com.business.swey.core.utils.Enum
import com.business.swey.core.utils.Enum.SizeType.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout

class SizeTileFilterSheetFragment: RoundedBottomSheetDialogBindingFragment<FragmentSizeTilesFilterBinding>() {

    override fun getLayout() = R.layout.fragment_size_tiles_filter

    override fun initViews(binding: FragmentSizeTilesFilterBinding) {
        createBlurredOverlay()
        binding.tlSizeTileType.addTab(binding.tlSizeTileType.newTab().setText("EU"))
        binding.tlSizeTileType.addTab(binding.tlSizeTileType.newTab().setText("US"))
        binding.tlSizeTileType.addTab(binding.tlSizeTileType.newTab().setText("UK"))

        binding.rvSizeTile.layoutManager = GridLayoutManager(this@SizeTileFilterSheetFragment.context, 5, GridLayoutManager.VERTICAL, false)
        val adapter = SizeTileFilterRecyclerViewAdapter()
        adapter.setDataSource(createSizeDataSource(eu))
        binding.rvSizeTile.adapter = adapter

        binding.tlSizeTileType.addOnTabSelectedListener(object:
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (binding.tlSizeTileType.selectedTabPosition) {
                    0 -> {
                        adapter.setDataSource(createSizeDataSource(eu))
                    }
                    1 -> {
                        adapter.setDataSource(createSizeDataSource(us))
                    }
                    else -> {
                        adapter.setDataSource(createSizeDataSource(uk))
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })

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
        val sizesList = ArrayList<SizeTileFilterItemDTO>()
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

    companion object {
        fun getInstance(): SizeTileFilterSheetFragment = SizeTileFilterSheetFragment()
    }

}