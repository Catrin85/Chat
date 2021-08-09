package com.katesin.testchat.ui.user

import android.os.Bundle
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseActivity
import com.katesin.testchat.ui.core.BaseFragment

class UserActivity : BaseActivity() {
    override var fragment: BaseFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}
