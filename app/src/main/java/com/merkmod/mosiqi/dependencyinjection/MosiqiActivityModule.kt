package com.merkmod.mosiqi.dependencyinjection

import com.merkmod.mosiqi.annotations.ActivityScope
import com.merkmod.mosiqi.utilities.BasicActivity
import dagger.Module
import dagger.Provides

/**
 * Created by rkodekar on 6/21/17.
 */
@Module
class MosiqiActivityModule(val baseActivity: BasicActivity) {

    @Provides
    @ActivityScope
    fun provideActivity() = baseActivity
}