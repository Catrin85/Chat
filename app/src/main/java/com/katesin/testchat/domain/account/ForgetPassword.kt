package com.katesin.testchat.domain.account

import com.katesin.testchat.domain.interactor.UseCase
import com.katesin.testchat.domain.type.None
import javax.inject.Inject

class ForgetPassword @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, ForgetPassword.Params>() {

    override suspend fun run(params: Params) = accountRepository.forgetPassword(params.email)

    data class Params(val email: String)
}