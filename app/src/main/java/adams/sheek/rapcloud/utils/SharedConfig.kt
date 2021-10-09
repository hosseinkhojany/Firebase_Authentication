package adams.sheek.rapcloud.utils

import adams.sheek.rapcloud.App
import adams.sheek.rapcloud.data.model.User
import android.content.Context
import android.content.SharedPreferences

object SharedConfig {

    //region config
    val pref by lazy { App.context().getSharedPreferences("cfg" , Context.MODE_PRIVATE) }

    fun setPreference(key: String?, value: Any?) {
        val editor: SharedPreferences.Editor =
            pref.edit()
        when (value) {
            is Int -> editor.putInt(
                key,
                (value as Int?)!!
            )
            is String -> editor.putString(
                key,
                value as String?
            )
            is Boolean -> editor.putBoolean(
                key,
                (value as Boolean?)!!
            )
            is Long -> editor.putLong(
                key,
                (value as Long?)!!
            )
            is Set<*> -> editor.putStringSet(key, value as Set<String?>?)
        }
        editor.apply()
    }

    fun getInt(key: String?, defaultValue: Int): Int {
        return pref.getInt(key, defaultValue)
    }

    fun getString(key: String, defaultValue: String): String {
        return pref.getString(key, defaultValue) ?: defaultValue
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return pref.getBoolean(key, defaultValue)
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        return pref.getLong(key, defaultValue)
    }

    fun getStringSet(key: String?, defaultValue: Set<String?>?): Set<String?>? {
        return pref.getStringSet(key, defaultValue)
    }

    fun clearTag(keyName: String?) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.remove(keyName)
        editor.apply()
    }

    fun clear(): Boolean {
        return pref.edit().clear().commit()
    }

    fun contain(key: String?): Boolean {
        return pref.contains(key)
    }

    fun RemovingSinglePreference(key: String?) {
        pref.edit().remove(key).apply()
    }
    //endregion


    //region variables
    private const val USER_DISPLAY_NAME = "USER_DISPLAY_NAME"
    private const val USER_EMAIL = "USER_EMAIL"
    private const val USER_PHONE_NUMBER = "USER_PHONE_NUMBER"
    private const val USER_PHONO_URL = "USER_PHONO_URL"
    private const val USER_UID = "USER_UID"



    //endregion



    //region setter&getters
    fun setUser(user: User){
        when(true){
            user.displayName.isNotEmpty() -> setPreference(USER_DISPLAY_NAME, user.displayName)
            user.email.isNotEmpty() -> setPreference(USER_EMAIL, user.email)
            user.phoneNumber.isNotEmpty() -> setPreference(USER_PHONE_NUMBER, user.phoneNumber)
            user.photoUrl.isNotEmpty() -> setPreference(USER_PHONO_URL, user.photoUrl)
            user.uId.isNotEmpty() -> setPreference(USER_UID, user.uId)
        }
    }
    fun getUser(): User{
        return User(
            getString(USER_DISPLAY_NAME, ""),
            getString(USER_EMAIL, ""),
            getString(USER_PHONE_NUMBER, ""),
            getString(USER_PHONO_URL, ""),
            getString(USER_UID, "")
        )
    }



    //endregion

}
