package com.katesin.testchat.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.katesin.testchat.R
import com.katesin.testchat.domain.type.None
import com.katesin.testchat.presentation.viewmodel.AccountViewModel
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseFragment
import com.katesin.testchat.ui.core.ext.onFailure
import com.katesin.testchat.ui.core.ext.onSuccess
import kotlinx.android.synthetic.main.fragment_forget_password.*

class ForgetPasswordFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_forget_password
    override val titleToolbar: Int = R.string.screen_forget_password

    lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel = viewModel {
            onSuccess(forgetPasswordData, ::onPasswordSent)
            onFailure(failureData, ::handleFailure)
        }

        btnSendPassword.setOnClickListener {
            sendPassword()
        }
    }

    private fun sendPassword() {
        if (etEmail.testValidity()) {
            val email = etEmail.text.toString()
            accountViewModel.forgetPassword(email)
        }
    }

    private fun onPasswordSent(none: None?) {
        Toast.makeText(requireContext(), getString(R.string.email_sent), Toast.LENGTH_SHORT).show()
        activity?.finish()
    }
}