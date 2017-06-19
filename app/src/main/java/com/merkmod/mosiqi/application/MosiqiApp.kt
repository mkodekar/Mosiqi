package com.merkmod.mosiqi.application

import android.app.Application
import android.content.Context
import com.merkmod.mosiqi.dependencyinjection.AppComponent
import com.merkmod.mosiqi.dependencyinjection.AppModule
import com.merkmod.mosiqi.dependencyinjection.DaggerAppComponent

/**
 * Created by rkodekar on 6/19/17.
 */
class MosiqiApp: Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        resolveDependency()
        super.onCreate()
    }

    fun resolveDependency() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this@MosiqiApp)).build()
    }

    companion object {
        fun getAppComponent(context: Context) = (context.applicationContext as MosiqiApp).appComponent
    }
}