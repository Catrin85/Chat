package com.katesin.testchat.ui.firebase

import android.os.Handler
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.katesin.testchat.domain.account.UpdateToken
import com.katesin.testchat.ui.App
import javax.inject.Inject
import android.os.Looper


class FirebaseService : FirebaseMessagingService() {

    @Inject
    lateinit var updateToken: UpdateToken

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        super.onCreate()
        App.appComponent.inject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Handler(Looper.getMainLooper()).post {
            notificationHelper.sendNotification(remoteMessage)

        }
    }

    override fun onNewToken(token: String) {
        Log.e("fb token", ": $token")
        if (token != null) {
            updateToken(UpdateToken.Params(token))
        }
    }
}
