package com.katesin.testchat.cache

import com.katesin.testchat.data.account.AccountCache
import com.katesin.testchat.domain.account.AccountEntity
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.Failure
import com.katesin.testchat.domain.type.None
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager, private val chatDatabase: ChatDatabase) : AccountCache {

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }

    override fun logout(): Either<Failure, None> {
        chatDatabase.clearAllTables()
        return prefsManager.removeAccount()
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return prefsManager.getAccount()
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> {
        return prefsManager.saveAccount(account)
    }

    override fun checkAuth(): Either<Failure, Boolean> {
        return prefsManager.containsAnyAccount()
    }
}