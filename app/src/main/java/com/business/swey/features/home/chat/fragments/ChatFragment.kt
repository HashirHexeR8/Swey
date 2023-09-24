package com.business.swey.features.home.chat.fragments

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.business.swey.R
import com.business.swey.core.base.BindingFragment
import com.business.swey.core.models.PersonQuickPayDto
import com.business.swey.databinding.FragmentChatBinding
import com.business.swey.features.home.chat.adapters.QuickPayAdapter
import com.google.android.material.tabs.TabLayout

class ChatFragment : BindingFragment<FragmentChatBinding>() {

    private var quickPayAdapter: QuickPayAdapter? = QuickPayAdapter(
        list = getData(),
        onSelectionCallback = ::onQuickPayClickCallback
    )

    private val chatsFragment = AllChatsFragment()
    private val friendsCartsFragment = FriendsCartsFragment()

    override fun getLayout() = R.layout.fragment_chat

    override fun initViews(binding: FragmentChatBinding) {
        binding.recyclerViewQuickPay.adapter = quickPayAdapter
        setUpTabs(binding)
    }

    override fun setListeners(binding: FragmentChatBinding) {
        binding.btQuickPaySetting.setOnClickListener {
            Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show()
        }
        binding.btnChatOptions.setOnClickListener {
            ChatOptionsDialog.getInstance().show(childFragmentManager, ChatOptionsDialog.TAG)
        }
    }

    private fun onQuickPayClickCallback(personQuickPayDto: PersonQuickPayDto) {
        Toast.makeText(requireContext(), personQuickPayDto.title, Toast.LENGTH_SHORT).show()
    }

    private fun setUpTabs(binding: FragmentChatBinding) {
        binding.tabLayoutChats.addTab(binding.tabLayoutChats.newTab().setText("All Chats"))
        binding.tabLayoutChats.addTab(binding.tabLayoutChats.newTab().setText("All Shopping Carts"))
        binding.tabLayoutChats.addTab(binding.tabLayoutChats.newTab().setText("•••"))

        loadFragment(chatsFragment) //Default
        binding.tabLayoutChats.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        loadFragment(chatsFragment)
                        binding.btnChatOptions.isVisible = true
                    }

                    1 -> {
                        loadFragment(friendsCartsFragment)
                        binding.btnChatOptions.isVisible = false
                    }
                    2 -> {
                        binding.btnChatOptions.isVisible = false
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragmentContainer,
            fragment
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getData(): List<PersonQuickPayDto> {
        return listOf(
            PersonQuickPayDto(R.drawable.ic_quick_pay, "Send To:"),
            PersonQuickPayDto(R.drawable.ic_person_1, "Barbara"),
            PersonQuickPayDto(R.drawable.ic_person_2, "Sebastian"),
            PersonQuickPayDto(R.drawable.ic_person_3, "Kale"),
            PersonQuickPayDto(R.drawable.ic_person_4, "Caremen"),
            PersonQuickPayDto(R.drawable.ic_person_5, "Ruby")
        )
    }

    companion object {
        fun getInstance(): ChatFragment = ChatFragment()
    }
}