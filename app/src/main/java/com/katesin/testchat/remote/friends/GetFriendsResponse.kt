package com.katesin.testchat.remote.friends

import com.katesin.testchat.domain.friends.FriendEntity
import com.katesin.testchat.remote.core.BaseResponse

class GetFriendsResponse (
    success: Int,
    message: String,
    val friends: List<FriendEntity>
) : BaseResponse(success, message)