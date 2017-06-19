package com.merkmod.mosiqi.dependencyinjection

import android.content.Context
import android.content.SharedPreferences
import com.merkmod.mosiqi.application.MosiqiApp
import com.merkmod.mosiqi.utilities.Utils
import com.merkmod.mosiqi.widgets.*
import com.wealthfront.magellan.Navigator
import com.wealthfront.magellan.Screen
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rkodekar on 6/19/17.
 */

@Module
class AppModule(var mosiqiApp: MosiqiApp) {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        val screenList = ArrayList<Screen<*>>()
        screenList.apply {
            add(SongWidget.SongScreen())
            add(AlbumsWidget.AlbumScreen())
            add(ArtistWidget.ArtistScreen())
        }
        return Navigator.withRoot(Base.BaseScreen(screenList.toList())).build()
    }

    @Provides
    @Singleton
    fun providePreferences(mosiqiApp: MosiqiApp): SharedPreferences {
        return mosiqiApp.getSharedPreferences(Utils.APP_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideMosiqiApp(): MosiqiApp = mosiqiApp
}