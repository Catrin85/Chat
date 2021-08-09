package com.katesin.testchat.remote.friends

import com.google.gson.annotations.SerializedName
import com.katesin.testchat.domain.friends.FriendEntity
import com.katesin.testchat.remote.core.BaseResponse

class GetFriendRequestsResponse (
    success: Int,
    message: String,
    @SerializedName("friend_requests")
    val friendsRequests: List<FriendEntity>
) : BaseResponse(success, message)