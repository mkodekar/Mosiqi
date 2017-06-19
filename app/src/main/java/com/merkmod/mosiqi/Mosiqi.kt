package com.merkmod.mosiqi

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.merkmod.mosiqi.application.MosiqiApp
import com.merkmod.mosiqi.utilities.BasicActivity
import com.wealthfront.magellan.Navigator
import kotlinx.android.synthetic.main.activity_mosiqi.*
import javax.inject.Inject

class Mosiqi : BasicActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosiqi)
        setSupportActionBar(toolbar)
        MosiqiApp.getAppComponent(this)?.inject(this)
        navigator.onCreate(this, savedInstanceState)
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
}
