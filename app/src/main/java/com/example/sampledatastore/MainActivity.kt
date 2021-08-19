package com.example.sampledatastore

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.sampledatastore.prefs.PrefsGetter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

        val prefsGetter = PrefsGetter(this)
        prefsGetter.writePrefs("Welcome","ToDataStore")

        prefsGetter.getPref1().asLiveData().observe(this, {
            Toast.makeText(applicationContext, "Pref1: $it", Toast.LENGTH_LONG).show()
        })

        prefsGetter.getPref2().asLiveData().observe(this, {
            Toast.makeText(applicationContext, "Pref2: $it", Toast.LENGTH_LONG).show()
        })
    }
}
