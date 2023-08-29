package com.app.signal.domain.model.photo

import android.net.Uri

interface Photo {
    val id: String
    val img: Image
    val title: String
    val isFavourite: Boolean
}

interface Image {
    val smallImageUrl: Uri?
    val largeImageUrl: Uri?
    val thumbNailUrl: Uri?
}