package com.katesin.testchat.ui.messages

import android.os.Bundle
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseActivity
import com.katesin.testchat.ui.core.BaseFragment

class MessagesActivity : BaseActivity() {
    override var fragment: BaseFragment = MessagesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}
