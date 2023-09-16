package com.business.swey.core.models

import java.time.Instant
import java.util.Date

class ChatPayDto(
    override val id: Int,
    override val messageStatus: ChatStatusType? = ChatStatusType.UNKNOWN,
    override val chatDirection: ChatDirection? = ChatDirection.SENT,
    override var chatType: ChatType? = ChatType.TEXT,
    override val time: Date? = Date.from(Instant.now()),
    val paymentDirection: PaymentDirection,
    val amount: Double
) : ChatDto(
    id = id,
    messageStatus = messageStatus,
    chatDirection = chatDirection,
    chatType = chatType,
    time = time
)

enum class PaymentDirection{
    SEND,
    REQUEST
}