package com.business.swey.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.business.swey.features.home.chat.fragments.ChatFragment
import com.business.swey.features.home.shop.fragments.ShopFragment

class HomeViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ShopFragment.getInstance()
            1 -> ChatFragment.getInstance()
            else -> throw IllegalStateException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Shop"
            1 -> "Chat"
            else -> throw IllegalStateException()
        }
    }
}