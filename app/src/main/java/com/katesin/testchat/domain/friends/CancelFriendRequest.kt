package com.katesin.testchat.domain.friends

import com.katesin.testchat.domain.interactor.UseCase
import com.katesin.testchat.domain.type.None
import javax.inject.Inject

class CancelFriendRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, FriendEntity>() {

    override suspend fun run(params: FriendEntity) = friendsRepository.cancelFriendRequest(params)
}