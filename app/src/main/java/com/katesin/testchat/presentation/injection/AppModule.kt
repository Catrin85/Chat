package com.katesin.testchat.presentation.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import com.katesin.testchat.data.account.AccountCache
import com.katesin.testchat.data.account.AccountRemote
import com.katesin.testchat.data.account.AccountRepositoryImpl
import com.katesin.testchat.data.friends.FriendsCache
import com.katesin.testchat.data.friends.FriendsRemote
import com.katesin.testchat.data.friends.FriendsRepositoryImpl
import com.katesin.testchat.data.media.MediaRepositoryImpl
import com.katesin.testchat.data.messages.MessagesCache
import com.katesin.testchat.data.messages.MessagesRemote
import com.katesin.testchat.data.messages.MessagesRepositoryImpl
import com.katesin.testchat.domain.account.AccountRepository
import com.katesin.testchat.domain.friends.FriendsRepository
import com.katesin.testchat.domain.media.MediaRepository
import com.katesin.testchat.domain.messages.MessagesRepository
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideFriendsRepository(remote: FriendsRemote, accountCache: AccountCache, friendsCache: FriendsCache): FriendsRepository {
        return FriendsRepositoryImpl(accountCache, remote, friendsCache)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(context: Context): MediaRepository {
        return MediaRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideMessagesRepository(remote: MessagesRemote, cache: MessagesCache, accountCache: AccountCache): MessagesRepository {
        return MessagesRepositoryImpl(remote, cache, accountCache)
    }
}