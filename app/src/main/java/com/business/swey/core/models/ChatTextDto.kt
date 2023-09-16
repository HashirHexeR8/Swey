package com.business.swey.core.models

import java.time.Instant
import java.util.Date

data class ChatTextDto(
    override val id: Int,
    override val messageStatus: ChatStatusType? = ChatStatusType.UNKNOWN,
    override val chatDirection: ChatDirection? = ChatDirection.SENT,
    override var chatType: ChatType? = ChatType.TEXT,
    override val time: Date? = Date.from(Instant.now()),
    val message: String,

    ) : ChatDto(
    id = id,
    messageStatus = messageStatus,
    chatDirection = chatDirection,
    chatType = chatType,
    time = time
)