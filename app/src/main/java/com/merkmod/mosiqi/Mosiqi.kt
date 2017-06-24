package com.merkmod.mosiqi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mcxiaoke.koi.ext.getActivity
import com.merkmod.mosiqi.application.MosiqiApp
import com.merkmod.mosiqi.dependencyinjection.DaggerMosiqiActivityComponent
import com.merkmod.mosiqi.dependencyinjection.MosiqiActivityModule
import com.merkmod.mosiqi.utilities.BasicActivity
import com.merkmod.mosiqi.utilities.PermissionUtil
import com.wealthfront.magellan.Navigator
import kotlinx.android.synthetic.main.activity_mosiqi.*
import timber.log.Timber
import javax.inject.Inject

const val WRITE_EXTERNAL = 0

class Mosiqi : BasicActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    @Inject
    lateinit var navigator: Navigator

    var bundle: Bundle? = null

    val PERMISSION_STORAGE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        val  component = DaggerMosiqiActivityComponent.builder()
                .mosiqiActivityModule(MosiqiActivityModule(this))
                .mosiqiComponent(MosiqiApp.injector(this).component)
                .build()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosiqi)
        setSupportActionBar(toolbar)
        bundle = savedInstanceState
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            navigator.onCreate(this, savedInstanceState)
        } else {
            requestStoragePermissions()
        }
    }

    fun requestStoragePermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Timber.d("displaying storage permission rationale to provide additional context")
            val layout: View = getActivity().findViewById<View>(R.id.rootLayout)
            Snackbar.make(layout, R.string.permission_storage_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok) {
                        ActivityCompat.requestPermissions(this@Mosiqi, PERMISSION_STORAGE, WRITE_EXTERNAL)
                    }.show()
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, WRITE_EXTERNAL)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_mosiqi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == WRITE_EXTERNAL) {
            Timber.i("Recieved Response from storage permissions request")

            if (PermissionUtil.verifyPermissions(grantResults)) {
                val layout: View = getActivity().findViewById<View>(R.id.rootLayout)
                Snackbar.make(layout, R.string.permission_storage_available, Snackbar.LENGTH_SHORT).show()
                navigator.onCreate(this@Mosiqi, bundle)
            } else{
                Timber.i("Storage permissions are not granted")
                val layout: View = getActivity().findViewById<View>(R.id.rootLayout)
                Snackbar.make(layout, R.string.permission_storage_unavailable, Snackbar.LENGTH_INDEFINITE).
                        setAction(R.string.ok) {
                            finish()
                        }.show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }
}
