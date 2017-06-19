package com.merkmod.mosiqi.dependencyinjection

import com.merkmod.mosiqi.Mosiqi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rkodekar on 6/19/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mosiqi: Mosiqi)
}