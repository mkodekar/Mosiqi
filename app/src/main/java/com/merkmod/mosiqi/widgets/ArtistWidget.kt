package com.merkmod.mosiqi.widgets

import android.content.Context
import android.view.View
import com.mcxiaoke.koi.ext.toast
import com.merkmod.mosiqi.R
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen
import kotlinx.android.synthetic.main.artist_screen.view.*

/**
 * Created by rkodekar on 6/19/17.
 */
object ArtistWidget {

    class ArtistScreen: Screen<ArtistView>() {
        override fun createView(context: Context?) = ArtistView(context!!)

        override fun onShow(context: Context?) {
            super.onShow(context)
            view.toast("Artist Screen")
            view.textView.text = "Hello world"
        }

    }

    class ArtistView(context: Context): BaseScreenView<ArtistScreen>(context) {

        init {
            View.inflate(context, R.layout.artist_screen, this)
        }
    }
}