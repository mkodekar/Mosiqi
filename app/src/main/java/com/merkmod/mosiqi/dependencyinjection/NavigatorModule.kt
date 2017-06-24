package com.merkmod.mosiqi.dependencyinjection

import com.merkmod.mosiqi.annotations.MosiqiScope
import com.merkmod.mosiqi.widgets.AlbumsWidget
import com.merkmod.mosiqi.widgets.ArtistWidget
import com.merkmod.mosiqi.widgets.Base
import com.merkmod.mosiqi.widgets.SongWidget
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.Screen
import dagger.Module
import dagger.Provides

/**
 * Created by rkodekar on 6/21/17.
 */

@Module
class NavigatorModule {

    @Provides
    @MosiqiScope
    fun provideNavigator() : Navigator {
        val screenList = ArrayList<Screen<*>>()
        screenList.apply {
            add(SongWidget.SongScreen())
            add(AlbumsWidget.AlbumScreen())
            add(ArtistWidget.ArtistScreen())
        }
        return Navigator.withRoot(Base.BaseScreen(screenList)).loggingEnabled(true).build()
    }
}