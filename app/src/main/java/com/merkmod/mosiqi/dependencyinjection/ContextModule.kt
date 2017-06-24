package com.merkmod.mosiqi.dependencyinjection

import android.content.Context
import com.merkmod.mosiqi.annotations.MosiqiScope
import dagger.Module
import dagger.Provides

/**
 * Created by rkodekar on 6/21/17.
 */

@Module
class ContextModule(val context: Context) {

    @Provides
    @MosiqiScope
    fun provideContext() = context.applicationContext
}