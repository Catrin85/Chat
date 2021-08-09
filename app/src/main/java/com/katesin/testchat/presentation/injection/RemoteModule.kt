package com.katesin.testchat.presentation.injection

import dagger.Module
import dagger.Provides
import com.katesin.testchat.BuildConfig
import com.katesin.testchat.data.account.AccountRemote
import com.katesin.testchat.data.friends.FriendsRemote
import com.katesin.testchat.data.messages.MessagesRemote
import com.katesin.testchat.remote.account.AccountRemoteImpl
import com.katesin.testchat.remote.core.Request
import com.katesin.testchat.remote.friends.FriendsRemoteImpl
import com.katesin.testchat.remote.messages.MessagesRemoteImpl
import com.katesin.testchat.remote.service.ApiService
import com.katesin.testchat.remote.service.ServiceFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        return AccountRemoteImpl(request, apiService)
    }

    @Singleton
    @Provides
    fun provideFriendsRemote(request: Request, apiService: ApiService): FriendsRemote {
        return FriendsRemoteImpl(request, apiService)
    }

    @Singleton
    @Provides
    fun provideMessagesRemote(request: Request, apiService: ApiService): MessagesRemote {
        return MessagesRemoteImpl(request, apiService)
    }
}