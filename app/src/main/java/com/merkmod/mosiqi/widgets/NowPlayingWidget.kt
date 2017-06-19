package com.merkmod.mosiqi.widgets

import android.content.Context
import android.view.View
import com.merkmod.mosiqi.R
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen

/**
 * Created by rkodekar on 6/19/17.
 */
object NowPlayingWidget {

    class NowPlayingScreen: Screen<NowPlayingView>() {
        override fun createView(context: Context?) = NowPlayingView(context!!)
    }

    class NowPlayingView(context: Context) : BaseScreenView<NowPlayingScreen>(context) {

        init {
            View.inflate(context, R.layout.now_playing, this)
        }
    }
}