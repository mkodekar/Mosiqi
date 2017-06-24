package com.merkmod.mosiqi.dependencyinjection

import android.content.SharedPreferences
import com.merkmod.mosiqi.annotations.MosiqiScope
import com.squareup.picasso.Picasso
import com.wealthfront.magellan.Navigator
import dagger.Component

/**
 * Created by rkodekar on 6/21/17.
 */

@MosiqiScope
@Component(modules = arrayOf(ContextModule::class, NavigatorModule::class, SharedPreferenceModule::class))
interface MosiqiComponent {

    fun getNavigator() : Navigator
    fun getSharedPreference(): SharedPreferences
}