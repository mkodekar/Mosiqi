package com.merkmod.mosiqi.utilities

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

/**
 * Created by rkodekar on 6/19/17.
 */
open class BasicActivity: AppCompatActivity(), LifecycleRegistryOwner {
    override fun getLifecycle() = LifecycleRegistry(this@BasicActivity)
}