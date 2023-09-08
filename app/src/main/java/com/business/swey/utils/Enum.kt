package com.business.swey.utils

class Enum {
    enum class ListingPageSectionType(val value: Int) {
        categoriesSection(60),
        normalProductSection(61),
        normalInvertedProductSection(62),
        verticalProductSection(63),
        horizontalProductSection(64);
    }

    enum class ProductItemType(val value: Int) {
        verticalGroupItem(60),
        verticalItem(61),
        horizontalHighPrefGroupItem(62),
        horizontalLowPrefGroupItem(63),
        horizontalItem(64);
    }

    enum class SizeType(val value: String) {
        uk("UK"),
        us("US"),
        eu("EU"),
        normal("Normal");
    }
}