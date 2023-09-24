package com.business.swey.core.models

import java.io.Serializable
import java.time.Instant
import java.util.Date

open class ChatDto(
    open val id: Int = 0,
    open val messageStatus: ChatStatusType? = ChatStatusType.UNKNOWN,
    open val chatDirection: ChatDirection? = ChatDirection.SENT,
    open var chatType: ChatType? = ChatType.TEXT,
    open val time: Date? = Date.from(Instant.now()),
    open var repliedTo: ChatTextDto? = null
) : Serializable

enum class ChatStatusType {
    DELIVERED,
    SEEN,
    UNKNOWN;
}

enum class ChatDirection{
    SENT,
    RECEIVED,
    UNKNOWN;
}

enum class ChatType{
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    FILE,
    LOCATION,
    QUICK_PAY,
    POLL,
    TIMESTAMP
}
