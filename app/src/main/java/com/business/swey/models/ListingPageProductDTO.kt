package com.business.swey.models

import com.business.swey.utils.Enum

class ListingPageProductSectionDTO {
    constructor(sectionName: String, sectionType: Enum.ListingPageSectionType, products: ArrayList<ListingPageProductDTO>) {
        this.sectionName = sectionName
        this.sectionType = sectionType
        this.products = products
    }

    var sectionName: String = ""
    var sectionType: Enum.ListingPageSectionType = Enum.ListingPageSectionType.normalProductSection
    var products: ArrayList<ListingPageProductDTO> = ArrayList()
}

class ListingPageProductDTO {

    constructor(itemImage: String, imageResId: Int, itemType: Enum.ProductItemType, itemPriority: Double) {
        this.itemImage = itemImage
        this.imageResId = imageResId
        this.itemType = itemType
        this.itemPriority = itemPriority
    }

    var itemImage: String = ""
    var imageResId: Int = 0
    var itemType: Enum.ProductItemType = Enum.ProductItemType.horizontalLowPrefGroupItem
    var itemPriority: Double = 0.0
}
