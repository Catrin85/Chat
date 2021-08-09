package com.katesin.testchat.remote.account

import com.katesin.testchat.domain.account.AccountEntity
import com.katesin.testchat.remote.core.BaseResponse

class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)