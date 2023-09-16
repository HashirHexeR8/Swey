package com.business.swey.core.models

import com.business.swey.core.utils.Enum

data class ListingPageProductSectionDTO(
    var sectionName: String = "",
    var sectionType: Enum.ListingPageSectionType = Enum.ListingPageSectionType.normalProductSection,
    var products: ArrayList<ListingPageProductDTO> = ArrayList()
)

data class ListingPageProductDTO(
    var itemImage: String = "",
    var imageResId: Int = 0,
    var itemType: Enum.ProductItemType = Enum.ProductItemType.horizontalLowPrefGroupItem,
    var itemPriority: Double = 0.0
)
