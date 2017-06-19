package com.merkmod.mosiqi.widgets

import android.content.Context
import android.view.View
import com.mcxiaoke.koi.ext.toast
import com.merkmod.mosiqi.R
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen

/**
 * Created by rkodekar on 6/19/17.
 */
object AlbumsWidget {

    class AlbumScreen: Screen<AlbumView>() {
        override fun createView(context: Context?)= AlbumView(context!!)

        override fun onShow(context: Context?) {
            super.onShow(context)
            view.toast("Albums Screen")
        }
    }

    class AlbumView(context: Context): BaseScreenView<AlbumScreen>(context) {
        init {
            View.inflate(context, R.layout.album_screen, this)
        }
    }
}