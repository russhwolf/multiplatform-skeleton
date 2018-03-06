package io.intrepid.multiskeleton.settings

interface UserSettings {
    fun getLastIp(): String

    fun setLastIp(ip: String)
}
