package com.katesin.testchat.ui.account

import android.os.Bundle
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseActivity
import com.katesin.testchat.ui.core.BaseFragment

class AccountActivity : BaseActivity() {
    override var fragment: BaseFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}
