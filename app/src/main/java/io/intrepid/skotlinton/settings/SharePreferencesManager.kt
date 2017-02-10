package io.intrepid.skotlinton.settings

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharePreferencesManager private constructor(context: Context) : UserSettings {

    private val preferences: SharedPreferences

    init {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override var lastIp: String?
        get() = preferences.getString(LAST_IP, null)
        set(ip) = preferences.edit().putString(LAST_IP, ip).apply()

    companion object {

        private val LAST_IP = "last_ip"

        private var instance: SharePreferencesManager? = null

        fun getInstance(context: Context): UserSettings {
            if (instance == null) {
                instance = SharePreferencesManager(context)
            }
            return instance
        }
    }
}
