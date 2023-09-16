package com.business.swey.features.onBoarding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.business.swey.R
import com.business.swey.databinding.ActivityOnboardingBinding
import com.business.swey.features.onBoarding.fragments.GetStartedBindingFragment


class OnboardingActivity : AppCompatActivity() {

    lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(LayoutInflater.from(this))
        setTheme(R.style.Activity_NoTitle)
        setContentView(binding.root)

        // Replace the initial content with the first onboarding fragment
        initiateOnBoarding()
    }

    private fun initiateOnBoarding() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.host_fragment, GetStartedBindingFragment.getInstance())
            .commit()
    }
}