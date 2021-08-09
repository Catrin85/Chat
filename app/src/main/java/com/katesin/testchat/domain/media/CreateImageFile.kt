package com.katesin.testchat.domain.media

import android.net.Uri
import com.katesin.testchat.domain.interactor.UseCase
import com.katesin.testchat.domain.type.None
import javax.inject.Inject

class CreateImageFile @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Uri, None>() {

    override suspend fun run(params: None) = mediaRepository.createImageFile()
}