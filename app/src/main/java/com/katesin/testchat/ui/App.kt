package com.katesin.testchat.ui

import android.app.Application
import dagger.Component
import com.katesin.testchat.presentation.injection.AppModule
import com.katesin.testchat.presentation.injection.CacheModule
import com.katesin.testchat.presentation.injection.RemoteModule
import com.katesin.testchat.presentation.injection.ViewModelModule
import com.katesin.testchat.ui.account.AccountActivity
import com.katesin.testchat.ui.account.AccountFragment
import com.katesin.testchat.ui.core.navigation.RouteActivity
import com.katesin.testchat.ui.firebase.FirebaseService
import com.katesin.testchat.ui.friends.FriendRequestsFragment
import com.katesin.testchat.ui.friends.FriendsFragment
import com.katesin.testchat.ui.home.HomeActivity
import com.katesin.testchat.ui.home.ChatsFragment
import com.katesin.testchat.ui.messages.MessagesActivity
import com.katesin.testchat.ui.messages.MessagesFragment
import com.katesin.testchat.ui.login.ForgetPasswordFragment
import com.katesin.testchat.ui.login.LoginFragment
import com.katesin.testchat.ui.register.RegisterActivity
import com.katesin.testchat.ui.register.RegisterFragment
import com.katesin.testchat.ui.user.UserActivity
import com.katesin.testchat.ui.user.UserFragment
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    //activities
    fun inject(activity: RegisterActivity)
    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)
    fun inject(activity: AccountActivity)
    fun inject(activity: MessagesActivity)
    fun inject(activity: UserActivity)

    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)
    fun inject(fragment: FriendsFragment)
    fun inject(fragment: FriendRequestsFragment)
    fun inject(fragment: AccountFragment)
    fun inject(fragment: MessagesFragment)
    fun inject(fragment: UserFragment)
    fun inject(fragment: ForgetPasswordFragment)

    //services
    fun inject(service: FirebaseService)
}