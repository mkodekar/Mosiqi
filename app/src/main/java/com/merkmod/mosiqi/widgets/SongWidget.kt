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
object SongWidget {

    class SongScreen: Screen<SongView>() {
        override fun createView(context: Context?) = SongView(context!!)

        override fun onShow(context: Context?) {
            super.onShow(context)
            view.toast("Songs Screen")
        }
    }

    class SongView(context: Context) : BaseScreenView<SongScreen>(context) {

        init {
            View.inflate(context, R.layout.song_screen, this)
        }
    }
}