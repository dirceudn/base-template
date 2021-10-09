package com.org.route

import android.content.Context
import android.content.Intent
import android.os.Bundle

class OnAlbumRoute : OnAlbumDetailRouteContract {
    private var albumId: Long = 0L
    override fun dataArgSetup(albumId: Long) {
        this.albumId = albumId
    }

    override fun presenter(fromContext: Context) {
        val bundle = Bundle().apply {
            putLong(ARG_DATA, albumId)
        }
        val intent = Intent(albumDetailIntentPath).apply {
            putExtra(ARG_BUNDLE, bundle)
        }

        with(fromContext) {
            startActivity(intent)
        }
    }

    companion object {
        const val ARG_DATA = "data"
        const val ARG_BUNDLE = "arg"
        const val albumDetailIntentPath = "com.org.albumdetail.AlbumDetailActivity"
    }
}