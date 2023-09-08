package com.business.swey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.business.swey.databinding.ActivityOnboardingBinding
import com.business.swey.listingPage.ListingPageActivity

class OnboardingActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnboardingBinding.inflate(LayoutInflater.from(this@OnboardingActivity))
        setContentView(viewBinding.root)
        viewBinding.btnContinue.setOnClickListener {
            val intent = Intent(this@OnboardingActivity, ListingPageActivity::class.java)
            startActivity(intent)
        }
    }
}