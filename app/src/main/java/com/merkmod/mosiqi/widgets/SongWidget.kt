package com.merkmod.mosiqi.widgets

import android.arch.lifecycle.Observer
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import com.github.florent37.carpaccio.controllers.adapter.Holder
import com.github.florent37.carpaccio.controllers.adapter.OnItemClickListener
import com.mcxiaoke.koi.ext.getActivity
import com.mcxiaoke.koi.ext.toast
import com.merkmod.mosiqi.R
import com.merkmod.mosiqi.application.MosiqiApp
import com.merkmod.mosiqi.dependencyinjection.DaggerMosiqiActivityComponent
import com.merkmod.mosiqi.dependencyinjection.LoadersModule
import com.merkmod.mosiqi.dependencyinjection.MosiqiActivityComponent
import com.merkmod.mosiqi.dependencyinjection.MosiqiActivityModule
import com.merkmod.mosiqi.loaders.SongLoaders
import com.merkmod.mosiqi.loaders.Songs
import com.merkmod.mosiqi.utilities.BasicActivity
import com.wealthfront.magellan.BaseScreenView
import com.wealthfront.magellan.Screen
import kotlinx.android.synthetic.main.song_screen.view.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by rkodekar on 6/19/17.
 */
object SongWidget {

    class SongScreen: Screen<SongView>() {

        @Inject
        lateinit var songLoader: SongLoaders

        lateinit var component: MosiqiActivityComponent

        override fun createView(context: Context?) :SongView {
            component = DaggerMosiqiActivityComponent.builder()
                    .mosiqiActivityModule(MosiqiActivityModule(activity as BasicActivity))
                    .loadersModule(LoadersModule())
                    .mosiqiComponent(MosiqiApp.injector(activity).component)
                    .build()
            component.inject(this)
            return SongView(context!!)
        }

        override fun onShow(context: Context?) {
            super.onShow(context)
            songLoader.getSongData().observe(view.getActivity() as BasicActivity,  Observer {
                view.carpaccioLayout.mapList("songs", it?.sortedBy {
                    it.songName
                })
            })

            view.carpaccioLayout.onItemClick("songs", object: OnItemClickListener<Songs> {
                override fun isClickable(item: Songs?, position: Int, holder: Holder?) = true

                override fun onItemClick(item: Songs?, position: Int, holder: Holder?) {
                    val layout = view.findViewById<View>(R.id.root)
                    Snackbar.make(layout, item?.songName!!, Snackbar.LENGTH_SHORT).show()
                }

            })



        }
    }

    class SongView(context: Context) : BaseScreenView<SongScreen>(context) {

        init {
            View.inflate(context, R.layout.song_screen, this)
        }
    }
}