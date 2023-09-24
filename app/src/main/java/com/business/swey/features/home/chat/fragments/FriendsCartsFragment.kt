package com.business.swey.features.home.chat.fragments

import android.widget.Toast
import com.business.swey.R
import com.business.swey.core.base.BindingFragment
import com.business.swey.core.models.PersonChatDto
import com.business.swey.core.models.ChatDirection
import com.business.swey.core.models.ChatStatusType
import com.business.swey.core.models.ChatTextDto
import com.business.swey.core.models.ChatType
import com.business.swey.databinding.FragmentFriendsCartsBinding
import com.business.swey.features.home.chat.adapters.AllChatsAdapter
import java.time.Instant
import java.util.Date

class FriendsCartsFragment : BindingFragment<FragmentFriendsCartsBinding>() {

    private val adapter: AllChatsAdapter = AllChatsAdapter(
        list = getData(),
        onSelectionCallback = ::onCartClick
    )

    override fun getLayout() = R.layout.fragment_friends_carts

    override fun initViews(binding: FragmentFriendsCartsBinding) {
        binding.recyclerViewFriendsCarts.adapter = adapter
    }

    override fun setListeners(binding: FragmentFriendsCartsBinding) {}

    private fun onCartClick(personChatDto: PersonChatDto) {
        Toast.makeText(requireContext(), personChatDto.lastMessage.message, Toast.LENGTH_SHORT).show()
    }

    private fun getData(): List<PersonChatDto> {
        return listOf(
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_5,
                name = "Alice",
                messageCount = 4,
                lastMessage = ChatTextDto(
                    id = 1,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Nike Air Jordan 1 Retro High OG Sneakers"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_1,
                name = "Barbara",
                messageCount = 23,
                lastMessage = ChatTextDto(
                    id = 2,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Levi's 501 Original Fit Jeans"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_2,
                name = "Shirley",
                messageCount = 2,
                lastMessage = ChatTextDto(
                    id = 3,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Adidas Originals Trefoil Hoodie"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_3,
                name = "Goldberg",
                messageCount = 10,
                lastMessage = ChatTextDto(
                    id = 4,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "The North Face Resolve 2 Waterproof Jacket"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_4,
                name = "Steven",
                messageCount = 1,
                lastMessage = ChatTextDto(
                    id = 5,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Calvin Klein Modern Cotton Track Suit"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_3,
                name = "Hemisworth",
                messageCount = 13,
                lastMessage = ChatTextDto(
                    id = 6,
                    messageStatus = ChatStatusType.UNKNOWN,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Nike Men's Dri-FIT Therma Full-Zip Training Hoodie - Charcoal Gray"
                )
            )
        )
    }

    companion object {
        fun getInstance(): FriendsCartsFragment = FriendsCartsFragment()
    }
}
