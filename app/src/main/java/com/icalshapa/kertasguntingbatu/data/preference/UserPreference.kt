package com.icalshapa.kertasguntingbatu.data.preference

import android.content.Context
import android.content.SharedPreferences

class UserPreference(val context : Context) {
    private var preference : SharedPreferences = context.getSharedPreferences(NAME,MODE)

    companion object{
        private const val NAME = "Kertas Gunting Batu"
        private const val MODE = Context.MODE_PRIVATE
        private val PREF_USER_NAME = Pair("USER_NAME",null)
        private val PREF_USER_NAME_TWO = Pair("USER_NAME_TWO",null)
    }

    var userName : String?
        get() = preference.getString(PREF_USER_NAME.first, PREF_USER_NAME.second)
        set(value) = preference.edit {
            it.putString(PREF_USER_NAME.first,value)
        }

    var userNameTwo: String?
        get() = preference.getString(PREF_USER_NAME_TWO.first, PREF_USER_NAME_TWO.second)
        set(valueTwo) = preference.edit{
            it.putString(PREF_USER_NAME_TWO.first,valueTwo)
        }
}

private inline fun SharedPreferences.edit(operation : (SharedPreferences.Editor)->Unit){
    val editor = edit()
    operation(editor)
    editor.apply()
}