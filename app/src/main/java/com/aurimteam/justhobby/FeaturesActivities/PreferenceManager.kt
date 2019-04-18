package com.aurimteam.justhobby.FeaturesActivities

import android.content.Context
import android.content.SharedPreferences;
import android.R.id.edit
import android.content.Context.MODE_PRIVATE

class PreferenceManager(context: Context) {
    private val PREF_NAME: String = "IntroScreen"
    //ключ для первого запуска
    private val IS_FIRST_LAUNCH = "IsFirstLaunch"
    private val MODE_PRIVATE: Int = 0

    private var sharedPreference: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreference.edit()

    // вызовите внутри интро-активити,
    // чтобы установить значение false после первого запуска
    fun setIsFirstLaunch(isFirstLaunch: Boolean) {
        editor.putBoolean(IS_FIRST_LAUNCH, isFirstLaunch)
        editor.commit()
    }

    // возвращает установленное значение,
    // если значение не задано, то возвращается по умолчанию
    fun isFirstLaunch(): Boolean {
        return sharedPreference.getBoolean(IS_FIRST_LAUNCH, true)
    }
}