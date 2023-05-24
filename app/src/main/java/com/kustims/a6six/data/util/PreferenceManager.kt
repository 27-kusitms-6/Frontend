package com.kustims.a6six.data.util

import android.content.Context
import android.content.SharedPreferences
import android.telephony.PhoneNumberUtils
import com.google.android.material.internal.NavigationMenu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kustims.a6six.data.Constants.KEY_ACCESS_TOKEN
import com.kustims.a6six.data.Constants.KEY_REFRESH_TOKEN
import java.io.FilterInputStream


class PreferenceManager(
    private val context: Context
) {

    companion object {
        const val PREFERENCES_NAME = "LikeIt-pref"
        private const val DEFAULT_VALUE_STRING = ""
        private const val DEFAULT_VALUE_BOOLEAN = false
        private const val DEFAULT_VALUE_INT = -1
        private const val DEFAULT_VALUE_LONG = -1L
        private const val DEFAULT_VALUE_FLOAT = -1f

        const val KEY_ID_TOKEN = "ID_TOKEN"
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
        const val Filters = "filters"
        const val IMG_URL = "imageUrl"
        const val NAME = "name"
        const val NICKNAME = "nickname"
        const val PHONENUMBER = "phoneNumber"
        const val EMAIL = "email"
        const val BIRTHDATE ="birthDate"
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private val prefs by lazy { getPreferences(context) }

    private val editor by lazy { prefs.edit() }

    /**
     * String 값 저장
     * @param key
     * @param value
     */
    fun setString(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * boolean 값 저장
     * @param key
     * @param value
     */
    fun setBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * int 값 저장
     * @param key
     * @param value
     */
    fun setInt(key: String?, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * long 값 저장
     * @param key
     * @param value
     */
    fun setLong(key: String?, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * float 값 저장
     * @param key
     * @param value
     */
    fun setFloat(key: String?, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * String 값 로드
     * @param key
     * @return
     */
    fun getString(key: String?): String? {
        return prefs.getString(key, DEFAULT_VALUE_STRING)
    }

    /**
     * boolean 값 로드
     * @param key
     * @return
     */
    fun getBoolean(key: String?): Boolean {
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN)
    }

    /**
     * int 값 로드
     * @param key
     * @return
     */
    fun getInt(key: String?): Int {
        return prefs.getInt(key, DEFAULT_VALUE_INT)
    }

    /**
     * long 값 로드
     * @param key
     * @return
     */
    fun getLong(key: String?): Long {
        return prefs.getLong(key, DEFAULT_VALUE_LONG)
    }

    /**
     * float 값 로드
     * @param key
     * @return
     */
    fun getFloat(key: String?): Float {
        return prefs.getFloat(key, DEFAULT_VALUE_FLOAT)
    }

    /**
     * 키 값 삭제
     * @param key
     */
    fun removeKey(key: String?) {
        editor.remove(key)
        editor.apply()
    }

    /**
     * 모든 저장 데이터 삭제
     */
    fun clear() {
        editor.clear()
        editor.apply()
    }

    fun putAccessToken(accessToken: String) {
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun putAuthToken(accessToken: String, refreshToken: String) {
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }

    fun putFilters(filters: List<String>) {
        val jsonString = Gson().toJson(filters)
        editor.putString(Filters, jsonString)
        editor.apply()
    }

    fun getFilters(): List<String> {
        val jsonString = prefs.getString(Filters, null)
        return if (jsonString != null) {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(jsonString, type)
        } else {
            emptyList()
        }
    }

    fun putInfo(imageUrl:String?, name:String?, nickname: String?, email: String?, phoneNum: String?, birthDate: String?) {
        editor.putString(IMG_URL, imageUrl)
        editor.putString(NAME, name)
        editor.putString(NICKNAME, nickname)
        editor.putString(EMAIL, email)
        editor.putString(PHONENUMBER, phoneNum)
        editor.putString(BIRTHDATE, birthDate)
        editor.apply()
    }

    fun getName(): String? {
        return prefs.getString(NAME, null)
    }

    fun getNickname(): String? {
        return prefs.getString(NICKNAME, null)
    }

    fun getImgUrl(): String? {
        return prefs.getString(IMG_URL, null)
    }

    fun getEmail(): String? {
        return prefs.getString(EMAIL, null)
    }

    fun getPhoneNum(): String? {
        return prefs.getString(PHONENUMBER, null)
    }

    fun getbirthDate(): String? {
        return prefs.getString(BIRTHDATE, null)
    }
}