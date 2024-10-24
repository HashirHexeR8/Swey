package com.business.swey.core.utils

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

    enum class QuickPayItemType(val value: Int) {
        QUICK_PAY_ICON(60),
        QUICK_PAY_PERSON(61);
    }
    enum class CartItemType(val value: Int) {
        CART_ITEM(10);
    }

    enum class UserPictureItemType(val value: Int) {
        USER(20);
    }

    enum class CreditCardItemType(val value: Int) {
        CARD(30);
    }

    enum class SizeType(val value: String) {
        uk("UK"),
        us("US"),
        eu("EU"),
        normal("Normal");
    }

    enum class FilterTagItemType(val value: Int) {
        FILTER_TAG(40);
    }


    enum class ShopNowItemType(val value: Int) {
        SHOP_NOW(50);
    }
}