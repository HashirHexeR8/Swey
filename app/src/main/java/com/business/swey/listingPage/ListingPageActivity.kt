package com.business.swey.listingPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.R
import com.business.swey.databinding.ActivityListingPageBinding
import com.business.swey.listingDetails.FilterSheetFragment
import com.business.swey.listingDetails.ListingDetailActivity
import com.business.swey.listingPage.adapter.ListingPageRecyclerViewAdapter
import com.business.swey.models.ListingPageProductDTO
import com.business.swey.models.ListingPageProductSectionDTO
import com.business.swey.utils.Enum


class ListingPageActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityListingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityListingPageBinding.inflate(LayoutInflater.from(this@ListingPageActivity))
        setContentView(viewBinding.root)
        viewBinding.tlListingPageTab.addTab(viewBinding.tlListingPageTab.newTab().setText("Shop"))
        viewBinding.tlListingPageTab.addTab(viewBinding.tlListingPageTab.newTab().setText("Chat"))
        val layoutManager = LinearLayoutManager(this@ListingPageActivity, LinearLayoutManager.VERTICAL, false)
        viewBinding.rvListingPage.layoutManager = layoutManager
        viewBinding.rvListingPage.adapter = ListingPageRecyclerViewAdapter(createDataSource(), { product ->
            val dialogFragment = ListingPageProductDetailDialogFragment(product.imageResId)
            dialogFragment.show(supportFragmentManager, "signature")
        }, { product ->
            val intent = Intent(this@ListingPageActivity, ListingDetailActivity::class.java)
            startActivity(intent)
        })
        val searchImgId = resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchImageIcon = viewBinding.searchView.findViewById<ImageView>(searchImgId)
        searchImageIcon.setImageResource(R.drawable.ic_search)

        viewBinding.ibFilterButton.setOnClickListener {
            FilterSheetFragment.create().show(supportFragmentManager, "FilterSheetFragment")
        }

        viewBinding.rvListingPage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    viewBinding.tlListingPageTab.visibility = View.GONE
//                } else {
//                    viewBinding.tlListingPageTab.visibility = View.VISIBLE
//                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        })

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