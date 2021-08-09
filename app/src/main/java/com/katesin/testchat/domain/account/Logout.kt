package com.katesin.testchat.domain.account

import com.katesin.testchat.domain.interactor.UseCase
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.Failure
import com.katesin.testchat.domain.type.None
import javax.inject.Inject

class Logout @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, None>() {

    override suspend fun run(params: None): Either<Failure, None> = accountRepository.logout()
}