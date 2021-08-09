package com.katesin.testchat.remote.messages

import com.katesin.testchat.domain.messages.MessageEntity
import com.katesin.testchat.remote.core.BaseResponse

class GetMessagesResponse (
    success: Int,
    message: String,
    val messages: List<MessageEntity>
) : BaseResponse(success, message)