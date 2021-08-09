package com.katesin.testchat.domain.media

import android.graphics.Bitmap
import android.net.Uri
import com.katesin.testchat.domain.type.Either
import com.katesin.testchat.domain.type.Failure

interface MediaRepository {
    fun createImageFile(): Either<Failure, Uri>
    fun encodeImageBitmap(bitmap: Bitmap?): Either<Failure, String>
    fun getPickedImage(uri: Uri?): Either<Failure, Bitmap>
}