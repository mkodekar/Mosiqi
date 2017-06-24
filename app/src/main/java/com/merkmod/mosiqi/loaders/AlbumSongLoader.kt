package com.merkmod.mosiqi.loaders

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.provider.BaseColumns
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import co.metalab.asyncawait.async
import com.mcxiaoke.koi.ext.intValue
import com.mcxiaoke.koi.ext.longValue
import com.mcxiaoke.koi.ext.mapAndClose
import com.mcxiaoke.koi.ext.stringValue
import com.merkmod.mosiqi.utilities.Utils


/**
 * Created by rkodekar on 6/23/17.
 */
class AlbumSongLoader(application: Application): AndroidViewModel(application) {

    var albumSongLiveData = MutableLiveData<List<Songs>>()


    fun getAlbumSongData(albumId: Long) : LiveData<List<Songs>> {
        val  list = getAlbumSongList(getApplication(), albumId)
        albumSongLiveData.value = list
        return albumSongLiveData
    }

    fun getAlbumSongList(context: Context, albumId: Long):List<Songs> {
        val selection = StringBuilder()
        selection.append(AudioColumns.IS_MUSIC + "=1")
        selection.append(" AND " + AudioColumns.TITLE + " != ''")
        selection.append(" AND " + AudioColumns.ALBUM_ID + "=" + albumId)
        val albumSongCursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(
                        BaseColumns._ID,
                        AudioColumns.TITLE,
                        AudioColumns.ARTIST,
                        AudioColumns.ALBUM,
                        AudioColumns.ALBUM_ID,
                        AudioColumns.DURATION
                ), selection.toString(), null, null)
        return albumSongCursor.mapAndClose {
            val uri = Utils.getAlbumUri(longValue(AudioColumns.ALBUM_ID))
            Songs(longValue(BaseColumns._ID), stringValue(AudioColumns.TITLE),
                    stringValue(AudioColumns.ARTIST), stringValue(AudioColumns.ALBUM),
                    uri, intValue(AudioColumns.DURATION))
        }.toList()
    }
}