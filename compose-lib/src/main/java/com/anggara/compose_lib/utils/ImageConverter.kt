package com.anggara.compose_lib.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

fun String.base64ToImageBitmap(): ImageBitmap {
    val decodedString = Base64.decode(this, Base64.DEFAULT)
    val bitmap: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    return bitmap.asImageBitmap()
}

fun ImageBitmap.toBase64(): String {
    val bitmap = this.asAndroidBitmap()
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}