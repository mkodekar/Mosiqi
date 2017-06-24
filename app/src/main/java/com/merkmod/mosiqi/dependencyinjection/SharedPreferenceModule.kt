package com.merkmod.mosiqi.dependencyinjection

import android.content.Context
import com.merkmod.mosiqi.annotations.MosiqiScope
import com.merkmod.mosiqi.utilities.Utils
import dagger.Module
import dagger.Provides

/**
 * Created by rkodekar on 6/21/17.
 */
@Module(includes = arrayOf(ContextModule::class))
class SharedPreferenceModule {

    @Provides
    @MosiqiScope
    fun provideSharedPreference(context: Context) = context.getSharedPreferences(Utils.APP_PREFS, Context.MODE_PRIVATE)
}