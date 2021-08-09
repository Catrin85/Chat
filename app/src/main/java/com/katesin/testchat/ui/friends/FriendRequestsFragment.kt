package com.katesin.testchat.ui.friends

import android.os.Bundle
import android.view.View
import com.katesin.testchat.R
import com.katesin.testchat.domain.friends.FriendEntity
import com.katesin.testchat.domain.type.None
import com.katesin.testchat.presentation.viewmodel.FriendsViewModel
import com.katesin.testchat.ui.App
import com.katesin.testchat.ui.core.BaseListFragment
import com.katesin.testchat.ui.core.ext.onFailure
import com.katesin.testchat.ui.core.ext.onSuccess

class FriendRequestsFragment : BaseListFragment() {
    override val viewAdapter = FriendRequestsAdapter()

    override val layoutId = R.layout.inner_fragment_list

    lateinit var friendsViewModel: FriendsViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        base {
            friendsViewModel = viewModel {
                onSuccess(friendRequestsData, ::handleFriendRequests)
                onSuccess(approveFriendData, ::handleFriendRequestAction)
                onSuccess(cancelFriendData, ::handleFriendRequestAction)
                onFailure(failureData, ::handleFailure)
            }
        }


        setOnItemClickListener { item, v ->
            (item as? FriendEntity)?.let {
                when (v.id) {
                    R.id.btnApprove -> {
                        showProgress()
                        friendsViewModel.approveFriend(it)
                    }
                    R.id.btnCancel -> {
                        showProgress()
                        friendsViewModel.cancelFriend(it)
                    }
                    else -> {
                        activity?.let { act ->
                            navigator.showUser(act, it)
                        }
                    }
                }
            }
        }

        friendsViewModel.getFriendRequests()
    }


    private fun handleFriendRequests(requests: List<FriendEntity>?) {
        hideProgress()
        if (requests != null && requests.isNotEmpty()) {
            viewAdapter.submitList(requests)
        }
    }

    private fun handleFriendRequestAction(none: None?) {
        hideProgress()
        friendsViewModel.getFriendRequests()
    }
}