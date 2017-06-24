package com.merkmod.mosiqi.utilities

import android.content.ContentUris
import android.media.MediaMetadataRetriever
import android.net.Uri

/**
 * Created by rkodekar on 6/19/17.
 */
object Utils {

    var APP_PREFS = "Mosiqi"

    fun getAlbumUri(albumId: Long) = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),albumId)

    fun getAlbumArtForFile(filePath: String): String {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(filePath)
        return mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
    }
}