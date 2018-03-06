package io.intrepid.multiskeleton.settings

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SharedPreferencesManager private constructor(context: Context) : UserSettings {
    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private val LAST_IP = "last_ip"
        private var instance: UserSettings? = null
        fun getInstance(context: Context): UserSettings {
            return instance ?: SharedPreferencesManager(context).apply { instance = this }
        }
    }

    override fun getLastIp(): String {
        return preferences.getString(LAST_IP, "")
    }

    override fun setLastIp(ip: String) {
        preferences.edit().putString(LAST_IP, ip).apply()
    }
}
