package com.merkmod.mosiqi.dependencyinjection

import android.arch.lifecycle.ViewModelProviders
import com.merkmod.mosiqi.Mosiqi
import com.merkmod.mosiqi.annotations.ActivityScope
import com.merkmod.mosiqi.loaders.AlbumLoaders
import com.merkmod.mosiqi.loaders.AlbumSongLoader
import com.merkmod.mosiqi.loaders.SongLoaders
import com.merkmod.mosiqi.utilities.BasicActivity
import com.merkmod.mosiqi.widgets.SongWidget
import dagger.Module
import dagger.Provides

/**
 * Created by rkodekar on 6/21/17.
 */

@Module(includes = arrayOf(MosiqiActivityModule::class))
class LoadersModule {

    @Provides
    @ActivityScope
    fun provideSongLoader(basicActivity: BasicActivity): SongLoaders = ViewModelProviders.of(basicActivity).get(SongLoaders::class.java)

    @Provides
    @ActivityScope
    fun provideAlbumLoader(basicActivity: BasicActivity): AlbumLoaders = ViewModelProviders.of(basicActivity).get(AlbumLoaders::class.java)

    @Provides
    @ActivityScope
    fun provideAlbumSongLoader(basicActivity: BasicActivity): AlbumSongLoader = ViewModelProviders.of(basicActivity).get(AlbumSongLoader::class.java)
}