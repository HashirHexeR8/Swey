package com.business.swey.core.models

data class PersonChatDto(
    val personDrawableId: Int,
    val name: String,
    val messageCount: Int?,
    val lastMessage: ChatTextDto
)