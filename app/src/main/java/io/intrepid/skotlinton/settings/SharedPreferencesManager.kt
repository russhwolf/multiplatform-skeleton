package io.intrepid.skotlinton.settings

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferencesManager private constructor(context: Context) : UserSettings {
    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    companion object {
        private val LAST_IP = "last_ip"
        private var instance: UserSettings? = null
        fun getInstance(context: Context): UserSettings {
            return instance ?: SharedPreferencesManager(context).apply { instance = this }
        }
    }

    override var lastIp: String
        get() = preferences.getString(LAST_IP, "")
        set(value) = preferences.edit().putString(LAST_IP, value).apply()
}
