package com.business.swey.core.models

import java.time.Instant
import java.util.Date

data class ChatVideoDto(
    override val id: Int,
    override val messageStatus: ChatStatusType? = ChatStatusType.UNKNOWN,
    override val chatDirection: ChatDirection? = ChatDirection.SENT,
    override var chatType: ChatType? = ChatType.TEXT,
    override val time: Date? = Date.from(Instant.now()),
    val thumbnailUrl: String,
    val videoUri: String,
    val videoSize: String
) : ChatDto(
    id = id,
    messageStatus = messageStatus,
    chatDirection = chatDirection,
    chatType = chatType,
    time = time
)
