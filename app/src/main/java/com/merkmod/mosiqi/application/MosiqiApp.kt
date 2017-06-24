package com.merkmod.mosiqi.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.merkmod.mosiqi.dependencyinjection.*
import com.wealthfront.magellan.Navigator
import timber.log.Timber

/**
 * Created by rkodekar on 6/19/17.
 */
class MosiqiApp: Application() {

    lateinit var component: MosiqiComponent
    lateinit var navigator: Navigator
    lateinit var sharePreference: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        component = DaggerMosiqiComponent.builder().contextModule(ContextModule(this)).build()
        navigator = component.getNavigator()
        sharePreference = component.getSharedPreference()

    }

    companion object {
        fun injector(activity: Activity) = activity.application as MosiqiApp
    }
}