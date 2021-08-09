package com.katesin.testchat.domain.account

import com.katesin.testchat.domain.type.None
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.Failure
import com.katesin.testchat.domain.interactor.UseCase
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.email, params.name, params.password)
    }

    data class Params(val email: String, val name: String, val password: String)
}

