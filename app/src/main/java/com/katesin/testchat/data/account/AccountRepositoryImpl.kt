package com.katesin.testchat.data.account

import com.katesin.testchat.domain.account.AccountEntity
import com.katesin.testchat.domain.account.AccountRepository
import com.katesin.testchat.domain.type.*
import java.util.*

class AccountRepositoryImpl(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {

    override fun login(email: String, password: String): Either<Failure, AccountEntity> {
        return accountCache.getToken().flatMap {
            accountRemote.login(email, password, it)
        }.onNext {
            it.password = password
            accountCache.saveAccount(it)
        }
    }

    override fun logout(): Either<Failure, None> {
        return accountCache.logout()
    }

    override fun register(email: String, name: String, password: String): Either<Failure, None> {
        return accountCache.getToken().flatMap {
            accountRemote.register(email, name, password, it, Calendar.getInstance().timeInMillis)
        }
    }

    override fun forgetPassword(email: String): Either<Failure, None> {
        return accountRemote.forgetPassword(email)
    }


    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return accountCache.getCurrentAccount()
    }


    override fun updateAccountToken(token: String): Either<Failure, None> {
        accountCache.saveToken(token)

        return accountCache.getCurrentAccount()
            .flatMap { accountRemote.updateToken(it.id, token, it.token) }
    }

    override fun updateAccountLastSeen(): Either<Failure, None> {
        return accountCache.getCurrentAccount().flatMap {
            accountRemote.updateAccountLastSeen(it.id, it.token, Date().time)
        }
    }


    override fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity> {
        return accountCache.getCurrentAccount().flatMap {
            accountRemote.editUser(entity.id, entity.email, entity.name, entity.password,
                entity.status, it.token, entity.image)
        }.onNext {
            entity.image = it.image
            accountCache.saveAccount(entity)
        }
    }

    override fun checkAuth(): Either<Failure, Boolean> {
        return accountCache.checkAuth()
    }
}