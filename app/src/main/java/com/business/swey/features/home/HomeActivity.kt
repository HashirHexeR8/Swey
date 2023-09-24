package com.business.swey.features.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.business.swey.R
import com.business.swey.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(LayoutInflater.from(this@HomeActivity))
        setTheme(R.style.Activity_NoTitle)
        setContentView(viewBinding.root)

        setUpTabs()
    }

    private fun setUpTabs() {
        viewBinding.tlListingPageTab.addTab(viewBinding.tlListingPageTab.newTab().setText("Shop"))
        viewBinding.tlListingPageTab.addTab(viewBinding.tlListingPageTab.newTab().setText("Chat"))

        viewBinding.viewPagerHome.adapter = HomeViewPagerAdapter(supportFragmentManager)
        viewBinding.viewPagerHome.offscreenPageLimit = 2
        viewBinding.tlListingPageTab.setupWithViewPager(viewBinding.viewPagerHome)
    }
}