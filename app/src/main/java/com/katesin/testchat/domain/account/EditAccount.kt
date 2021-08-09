package com.katesin.testchat.domain.account

import com.katesin.testchat.domain.interactor.UseCase
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.Failure
import javax.inject.Inject

class EditAccount @Inject constructor(
    private val repository: AccountRepository
) : UseCase<AccountEntity, AccountEntity>() {

    override suspend fun run(params: AccountEntity): Either<Failure, AccountEntity> {
        return repository.editAccount(params)
    }
}