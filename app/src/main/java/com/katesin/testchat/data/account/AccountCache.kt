package com.katesin.testchat.data.account

import com.katesin.testchat.domain.account.AccountEntity
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.None
import com.katesin.testchat.domain.type.Failure

interface AccountCache {
    fun getToken(): Either<Failure, String>
    fun saveToken(token: String): Either<Failure, None>

    fun logout(): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>
    fun saveAccount(account: AccountEntity): Either<Failure, None>

    fun checkAuth(): Either<Failure, Boolean>
}