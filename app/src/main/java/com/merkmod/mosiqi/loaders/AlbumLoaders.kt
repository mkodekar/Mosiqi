package com.merkmod.mosiqi.loaders

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.provider.BaseColumns
import android.provider.MediaStore
import co.metalab.asyncawait.async
import com.mcxiaoke.koi.ext.intValue
import com.mcxiaoke.koi.ext.longValue
import com.mcxiaoke.koi.ext.mapAndClose
import com.mcxiaoke.koi.ext.stringValue
import io.mironov.smuggler.AutoParcelable

/**
 * Created by rkodekar on 6/23/17.
 */
data class Albums(var albumId: Long, var albumName: String, var artistName: String, var songNumber: Int, var year: String): AutoParcelable
class AlbumLoaders(application: Application): AndroidViewModel(application) {

    var albumLiveData = MutableLiveData<List<Albums>>()

    init {
        async {
            val list = getAlbumList(application)
            albumLiveData.value = list
        }
    }

    fun getAlbumData() = albumLiveData

    fun getAlbumList(context: Context) : List<Albums> {
        val albumCursor = context.contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                arrayOf(
                        BaseColumns._ID,
                        MediaStore.Audio.AlbumColumns.ALBUM,
                        MediaStore.Audio.AlbumColumns.ARTIST,
                        MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
                        MediaStore.Audio.AlbumColumns.FIRST_YEAR
                ), null , null, null)
        return albumCursor.mapAndClose {
            Albums(longValue(BaseColumns._ID), stringValue(MediaStore.Audio.AlbumColumns.ALBUM),
                    stringValue(MediaStore.Audio.AlbumColumns.ARTIST), intValue(MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS),
                    stringValue(MediaStore.Audio.AlbumColumns.FIRST_YEAR))
        }.toList()
    }

}