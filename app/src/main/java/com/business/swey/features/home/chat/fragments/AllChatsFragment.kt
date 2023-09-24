package com.business.swey.features.home.chat.fragments

import com.business.swey.R
import com.business.swey.core.base.BindingFragment
import com.business.swey.core.models.PersonChatDto
import com.business.swey.core.models.ChatStatusType
import com.business.swey.core.models.ChatDirection
import com.business.swey.core.models.ChatTextDto
import com.business.swey.core.models.ChatType
import com.business.swey.databinding.FragmentAllChatsBinding
import com.business.swey.features.home.chat.adapters.AllChatsAdapter
import java.time.Instant
import java.util.Date

class AllChatsFragment : BindingFragment<FragmentAllChatsBinding>() {

    private val adapter: AllChatsAdapter = AllChatsAdapter(
        list = getData(),
        onSelectionCallback = ::onChatClick
    )

    override fun getLayout() = R.layout.fragment_all_chats

    override fun initViews(binding: FragmentAllChatsBinding) {
        binding.recyclerViewPersonChat.adapter = adapter
    }

    override fun setListeners(binding: FragmentAllChatsBinding) {}

    private fun onChatClick(personChatDto: PersonChatDto) {
        openDialogSheet(ChatScreenFragment.getInstance(), ChatScreenFragment.TAG)
    }

    private fun getData(): List<PersonChatDto> {
        return listOf(
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_3,
                name = "Goldberg",
                messageCount = 10,
                lastMessage = ChatTextDto(
                    id = 1,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "I'm waiting for the last 2 hours man"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_1,
                name = "Barbara",
                messageCount = null,
                lastMessage = ChatTextDto(
                    id = 2,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.SENT,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Hey! how are you man"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_2,
                name = "Shirley",
                messageCount = null,
                lastMessage = ChatTextDto(
                    id = 3,
                    messageStatus = ChatStatusType.SEEN,
                    chatDirection = ChatDirection.SENT,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Yesterday was really busy for me!"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_3,
                name = "Hemisworth",
                messageCount = 3,
                lastMessage = ChatTextDto(
                    id = 4,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Let's have dinner tonight at NYC"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_4,
                name = "Steven",
                messageCount = 1,
                lastMessage = ChatTextDto(
                    id = 5,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "What's up Man! up for a few coding problems"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_5,
                name = "Alice",
                messageCount = 2,
                lastMessage = ChatTextDto(
                    id = 6,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Are you guys coming here..."
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_2,
                name = "Shirley",
                messageCount = null,
                lastMessage = ChatTextDto(
                    id = 7,
                    messageStatus = ChatStatusType.SEEN,
                    chatDirection = ChatDirection.SENT,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Yesterday was really busy for me!"
                )
            ),
            PersonChatDto(
                personDrawableId = R.drawable.ic_person_3,
                name = "Hemisworth",
                messageCount = 3,
                lastMessage = ChatTextDto(
                    id = 8,
                    messageStatus = ChatStatusType.DELIVERED,
                    chatDirection = ChatDirection.RECEIVED,
                    chatType = ChatType.TEXT,
                    time = Date.from(Instant.now()),
                    message = "Let's have dinner tonight at NYC"
                )
            )
        )
    }

    companion object {
        fun getInstance(): AllChatsFragment = AllChatsFragment()
    }
}
