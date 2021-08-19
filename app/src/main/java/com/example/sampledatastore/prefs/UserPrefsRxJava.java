package com.example.sampledatastore.prefs;

import android.content.Context;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;


public class UserPrefsRxJava {

    private final static Preferences.Key<Integer> INTEGER_KEY = PreferencesKeys.intKey("integer");
    private final static Preferences.Key<String> PREF_1 = PreferencesKeys.stringKey("pref1");
    private final static Preferences.Key<String> PREF_2 = PreferencesKeys.stringKey("pref2");

    private final RxDataStore<Preferences> dataStore;

    public UserPrefsRxJava(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, /*name=*/ "settings").build();
    }

    public void updateData() {
        dataStore.updateDataAsync(prefs -> {
            MutablePreferences mutablePreferences = prefs.toMutablePreferences();
            Integer currentInt = prefs.get(INTEGER_KEY);
            mutablePreferences.set(INTEGER_KEY, currentInt != null ? currentInt + 1 : 1);
            return Single.just(mutablePreferences);
        });
    }

    public void updateData(String val1, String val2) {
        dataStore.updateDataAsync(prefs -> {
            MutablePreferences mutablePreferences = prefs.toMutablePreferences();
            mutablePreferences.set(PREF_1, val1);
            mutablePreferences.set(PREF_2, val2);
            return Single.just(mutablePreferences);
        });
    }

    public void readPref1() {
        Flowable<String> pref1 =
                dataStore.data().map(prefs -> prefs.get(PREF_1));
    }

    public void readPref2() {
        Flowable<String> pref1 =
                dataStore.data().map(prefs -> prefs.get(PREF_2));
    }
}
