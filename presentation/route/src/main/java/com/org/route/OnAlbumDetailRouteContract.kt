package com.org.route

import android.content.Context

interface OnAlbumDetailRouteContract {

    fun dataArgSetup(albumId: Long)

    fun presenter(fromContext: Context)
}