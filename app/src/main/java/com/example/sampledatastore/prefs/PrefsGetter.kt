package com.example.sampledatastore.prefs

import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.sampledatastore.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PrefsGetter(activity : MainActivity) {
    private var userPrefsKotlin : UserPrefsKotlin = UserPrefsKotlin(activity)
    private val mContext = activity.applicationContext

    init {
        getPref1().asLiveData().observe(activity, {
            Toast.makeText(mContext, "Pref1 In Getter: $it", Toast.LENGTH_LONG).show()
        })

        getPref2().asLiveData().observe(activity, {
            Toast.makeText(mContext, "Pref2 In Getter: $it", Toast.LENGTH_LONG).show()
        })
    }

    fun writePrefs(val1: String, val2: String) {
        GlobalScope.launch {
            userPrefsKotlin.writePref(val1, val2)
        }
    }

    fun getPref1() = userPrefsKotlin.pref1

    fun getPref2() = userPrefsKotlin.pref2
}