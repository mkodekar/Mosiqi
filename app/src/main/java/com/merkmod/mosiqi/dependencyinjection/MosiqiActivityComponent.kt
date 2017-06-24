package com.merkmod.mosiqi.dependencyinjection

import com.merkmod.mosiqi.Mosiqi
import com.merkmod.mosiqi.annotations.ActivityScope
import com.merkmod.mosiqi.widgets.AlbumsWidget
import com.merkmod.mosiqi.widgets.ArtistWidget
import com.merkmod.mosiqi.widgets.SongWidget
import dagger.Component

/**
 * Created by rkodekar on 6/21/17.
 */

@ActivityScope
@Component(modules = arrayOf(MosiqiActivityModule::class, LoadersModule::class), dependencies = arrayOf(MosiqiComponent::class))
interface MosiqiActivityComponent {

    fun inject(mosiqi: Mosiqi)

    fun inject(songScreen: SongWidget.SongScreen)

    fun inject(albumScreen: AlbumsWidget.AlbumScreen)

    fun inject(artistScreen: ArtistWidget.ArtistScreen)
}