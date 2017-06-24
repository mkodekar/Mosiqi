package com.merkmod.mosiqi.loaders

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import co.metalab.asyncawait.async
import com.mcxiaoke.koi.ext.longValue
import com.mcxiaoke.koi.ext.mapAndClose
import com.mcxiaoke.koi.ext.stringValue
import com.merkmod.mosiqi.utilities.Utils
import io.mironov.smuggler.AutoParcelable


/**
 * Created by rkodekar on 6/21/17.
 */
data class Songs(var songId: Long, var songName:String, var artistName: String, var albumName: String, var albumId: Uri, var duration: Int): AutoParcelable
class SongLoaders(application: Application): AndroidViewModel(application) {

    var songLiveData = MutableLiveData<List<Songs>>()

    init {
        async {
            val  list = getSongList(application)
            songLiveData.value = list
        }
    }

    fun getSongData() = songLiveData

    private fun getSongList(context: Context): List<Songs> {
        val selection = StringBuilder()
        selection.append(AudioColumns.IS_MUSIC + "=1")
        selection.append(" AND " + AudioColumns.TITLE + " != ''")
        val songCursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                arrayOf(BaseColumns._ID,
                        AudioColumns.TITLE,
                        AudioColumns.ARTIST,
                        AudioColumns.ALBUM,
                        AudioColumns.ALBUM_ID), selection.toString(), null, null)
        return songCursor.mapAndClose {
            val uri = Utils.getAlbumUri(longValue(AudioColumns.ALBUM_ID))
            Songs(longValue(BaseColumns._ID), stringValue(AudioColumns.TITLE),
                    stringValue(AudioColumns.ARTIST), stringValue(AudioColumns.ALBUM),
                    uri, -1)
        }.toList()
    }

}