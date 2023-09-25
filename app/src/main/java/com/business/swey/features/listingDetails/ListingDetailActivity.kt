package com.business.swey.features.listingDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.business.swey.R
import com.business.swey.core.extensions.adjustTheme
import com.business.swey.databinding.ActivityListingDetailBinding
import com.business.swey.features.listingDetails.adapters.ListingDetailRecyclerViewAdapter
import com.business.swey.features.home.shop.adapter.ListingPageRecyclerViewAdapter
import com.business.swey.core.models.ListingPageProductDTO
import com.business.swey.core.models.ListingPageProductSectionDTO
import com.business.swey.core.utils.Enum
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class ListingDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityListingDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityListingDetailBinding.inflate(layoutInflater)
        adjustTheme()
        setContentView(viewBinding.root)

        viewBinding.rvProductImages.layoutManager = LinearLayoutManager(this@ListingDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        viewBinding.rvProductImages.adapter = ListingDetailRecyclerViewAdapter()

        val layoutManager = LinearLayoutManager(this@ListingDetailActivity, LinearLayoutManager.VERTICAL, false)
        viewBinding.rvSimilarItemsList.layoutManager = layoutManager
        viewBinding.rvSimilarItemsList.adapter = ListingPageRecyclerViewAdapter(createDataSource(), null, null)

        viewBinding.tlListingDetails.addTab(viewBinding.tlListingDetails.newTab().setText("Details"))
        viewBinding.tlListingDetails.addTab(viewBinding.tlListingDetails.newTab().setText("Similar"))
        viewBinding.tlListingDetails.addOnTabSelectedListener(object: OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (viewBinding.tlListingDetails.selectedTabPosition == 0) {
                    viewBinding.tvProductDetail.visibility = View.VISIBLE
                    viewBinding.rvSimilarItemsList.visibility = View.GONE
                    viewBinding.llItemPriceButtonContainer.visibility = View.VISIBLE
                }
                else {
                    viewBinding.tvProductDetail.visibility = View.GONE
                    viewBinding.rvSimilarItemsList.visibility = View.VISIBLE
                    viewBinding.llItemPriceButtonContainer.visibility = View.GONE
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
        viewBinding.ibBackButton.setOnClickListener {
            finish()
        }
    }

    fun createDataSource(): ArrayList<ListingPageProductSectionDTO> {

        var sectionsList = ArrayList<ListingPageProductSectionDTO>()
        //Section 1
        var s1ItemList = ArrayList<ListingPageProductDTO>()
        s1ItemList.add(ListingPageProductDTO("s1p1", R.drawable.s1p1, Enum.ProductItemType.horizontalHighPrefGroupItem, 0.6))
        s1ItemList.add(ListingPageProductDTO("s1p2", R.drawable.s1p2, Enum.ProductItemType.horizontalLowPrefGroupItem, 0.6))
        sectionsList.add(ListingPageProductSectionDTO("Section1", Enum.ListingPageSectionType.normalProductSection, s1ItemList))
        //Section 2
        var s2ItemList = ArrayList<ListingPageProductDTO>()
        s2ItemList.add(ListingPageProductDTO("s2p1", R.drawable.s2p1, Enum.ProductItemType.horizontalLowPrefGroupItem, 0.6))
        s2ItemList.add(ListingPageProductDTO("s2p2", R.drawable.s2p2, Enum.ProductItemType.horizontalHighPrefGroupItem, 0.6))
        sectionsList.add(ListingPageProductSectionDTO("Section1", Enum.ListingPageSectionType.normalInvertedProductSection, s2ItemList))
        //Section 3
        var s3ItemList = ArrayList<ListingPageProductDTO>()
        s3ItemList.add(ListingPageProductDTO("s3p1", R.drawable.s3p1, Enum.ProductItemType.horizontalLowPrefGroupItem, 0.6))
        s3ItemList.add(ListingPageProductDTO("s3p2", R.drawable.s3p2, Enum.ProductItemType.horizontalHighPrefGroupItem, 0.6))
        sectionsList.add(ListingPageProductSectionDTO("Section1", Enum.ListingPageSectionType.normalProductSection, s3ItemList))
        //Section 4
        var s4ItemList = ArrayList<ListingPageProductDTO>()
        s4ItemList.add(ListingPageProductDTO("s4p1", R.drawable.s4p1, Enum.ProductItemType.verticalGroupItem, 0.6))
        s4ItemList.add(ListingPageProductDTO("s4p2", R.drawable.s4p2, Enum.ProductItemType.verticalGroupItem, 0.6))
        s4ItemList.add(ListingPageProductDTO("s4p3", R.drawable.s4p3, Enum.ProductItemType.verticalItem, 0.6))
        sectionsList.add(ListingPageProductSectionDTO("Section1", Enum.ListingPageSectionType.verticalProductSection, s4ItemList))
        //Section 5
        var s5ItemList = ArrayList<ListingPageProductDTO>()
        s5ItemList.add(ListingPageProductDTO("s5p1", R.drawable.s5p1, Enum.ProductItemType.horizontalLowPrefGroupItem, 0.6))
        s5ItemList.add(ListingPageProductDTO("s5p2", R.drawable.s5p2, Enum.ProductItemType.horizontalHighPrefGroupItem, 0.6))
        sectionsList.add(ListingPageProductSectionDTO("Section1", Enum.ListingPageSectionType.normalProductSection, s5ItemList))
        return sectionsList
    }
}