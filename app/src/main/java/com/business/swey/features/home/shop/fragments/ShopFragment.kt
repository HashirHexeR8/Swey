package com.business.swey.features.home.shop.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.business.swey.R
import com.business.swey.core.models.ListingPageProductDTO
import com.business.swey.core.models.ListingPageProductSectionDTO
import com.business.swey.core.utils.Enum
import com.business.swey.databinding.FragmentShopBinding
import com.business.swey.features.checkout.fragments.CheckoutCartFragment
import com.business.swey.features.listingDetails.FilterBottomSheetFragment
import com.business.swey.features.listingDetails.ListingDetailActivity
import com.business.swey.features.home.shop.adapter.ListingPageRecyclerViewAdapter
import com.business.swey.features.settings.fragments.SettingsFragment

class ShopFragment : Fragment() {

    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_shop, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListingPage.adapter =
            ListingPageRecyclerViewAdapter(createDataSource(), { product ->
                val dialogFragment = ListingPageProductDetailDialogFragment.getInstance(product.imageResId)
                dialogFragment.show(childFragmentManager, "signature")
            }, ::onProductClick)
        val searchImgId = resources.getIdentifier("android:id/search_mag_icon", null, null)
        val searchImageIcon = binding.searchView.findViewById<ImageView>(searchImgId)
        searchImageIcon.setImageResource(R.drawable.ic_search)

        binding.ibFilterButton.setOnClickListener {
            FilterBottomSheetFragment.getInstance().show(childFragmentManager, "FilterSheetFragment")
        }

        binding.layoutBadge.count = "4"
        binding.btnCheckoutFloat.setOnClickListener {
            CheckoutCartFragment.getInstance().show(childFragmentManager, CheckoutCartFragment.TAG)
        }

        binding.userImage.setOnClickListener {
            SettingsFragment.getInstance().show(childFragmentManager, SettingsFragment.TAG)
        }

        binding.rvListingPage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    binding.tlListingPageTab.visibility = View.GONE
//                } else {
//                    binding.tlListingPageTab.visibility = View.VISIBLE
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

    private fun onProductClick(product: ListingPageProductDTO) {
        val intent = Intent(activity, ListingDetailActivity::class.java)
        startActivity(intent)
    }

    private fun createDataSource(): ArrayList<ListingPageProductSectionDTO> {

        val sectionsList = ArrayList<ListingPageProductSectionDTO>()
        //Section 1
        val s1ItemList = ArrayList<ListingPageProductDTO>()
        s1ItemList.add(
            ListingPageProductDTO(
                "s1p1",
                R.drawable.s1p1,
                Enum.ProductItemType.horizontalHighPrefGroupItem,
                0.6
            )
        )
        s1ItemList.add(
            ListingPageProductDTO(
                "s1p2",
                R.drawable.s1p2,
                Enum.ProductItemType.horizontalLowPrefGroupItem,
                0.6
            )
        )
        sectionsList.add(
            ListingPageProductSectionDTO(
                "Section1",
                Enum.ListingPageSectionType.normalProductSection,
                s1ItemList
            )
        )
        //Section 2
        val s2ItemList = ArrayList<ListingPageProductDTO>()
        s2ItemList.add(
            ListingPageProductDTO(
                "s2p1",
                R.drawable.s2p1,
                Enum.ProductItemType.horizontalLowPrefGroupItem,
                0.6
            )
        )
        s2ItemList.add(
            ListingPageProductDTO(
                "s2p2",
                R.drawable.s2p2,
                Enum.ProductItemType.horizontalHighPrefGroupItem,
                0.6
            )
        )
        sectionsList.add(
            ListingPageProductSectionDTO(
                "Section1",
                Enum.ListingPageSectionType.normalInvertedProductSection,
                s2ItemList
            )
        )
        //Section 3
        val s3ItemList = ArrayList<ListingPageProductDTO>()
        s3ItemList.add(
            ListingPageProductDTO(
                "s3p1",
                R.drawable.s3p1,
                Enum.ProductItemType.horizontalLowPrefGroupItem,
                0.6
            )
        )
        s3ItemList.add(
            ListingPageProductDTO(
                "s3p2",
                R.drawable.s3p2,
                Enum.ProductItemType.horizontalHighPrefGroupItem,
                0.6
            )
        )
        sectionsList.add(
            ListingPageProductSectionDTO(
                "Section1",
                Enum.ListingPageSectionType.normalProductSection,
                s3ItemList
            )
        )
        //Section 4
        val s4ItemList = ArrayList<ListingPageProductDTO>()
        s4ItemList.add(
            ListingPageProductDTO(
                "s4p1",
                R.drawable.s4p1,
                Enum.ProductItemType.verticalGroupItem,
                0.6
            )
        )
        s4ItemList.add(
            ListingPageProductDTO(
                "s4p2",
                R.drawable.s4p2,
                Enum.ProductItemType.verticalGroupItem,
                0.6
            )
        )
        s4ItemList.add(
            ListingPageProductDTO(
                "s4p3",
                R.drawable.s4p3,
                Enum.ProductItemType.verticalItem,
                0.6
            )
        )
        sectionsList.add(
            ListingPageProductSectionDTO(
                "Section1",
                Enum.ListingPageSectionType.verticalProductSection,
                s4ItemList
            )
        )
        //Section 5
        val s5ItemList = ArrayList<ListingPageProductDTO>()
        s5ItemList.add(
            ListingPageProductDTO(
                "s5p1",
                R.drawable.s5p1,
                Enum.ProductItemType.horizontalLowPrefGroupItem,
                0.6
            )
        )
        s5ItemList.add(
            ListingPageProductDTO(
                "s5p2",
                R.drawable.s5p2,
                Enum.ProductItemType.horizontalHighPrefGroupItem,
                0.6
            )
        )
        sectionsList.add(
            ListingPageProductSectionDTO(
                "Section1",
                Enum.ListingPageSectionType.normalProductSection,
                s5ItemList
            )
        )
        return sectionsList
    }

    companion object {
        fun getInstance(): ShopFragment = ShopFragment()
    }
}