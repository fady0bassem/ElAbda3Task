package com.fadybassem.elabda3task.data

import android.content.Context
import android.content.SharedPreferences
import com.fadybassem.elabda3task.Application
import com.fadybassem.elabda3task.utils.Constants

class PreferencesHelper {

    companion object {

        private lateinit var context: Context

        private fun getPref(): SharedPreferences {
            context = Application.applicationContext()
            return context.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
        }

        fun setPage(id: Int) {
            getPref().edit().putInt(Constants.PAGE, id).apply()
        }

        fun getPage(): Int? {
            return getPref().getInt(Constants.PAGE, 1)
        }

        fun setRank(id: Int) {
            getPref().edit().putInt(Constants.RANK, id).apply()
        }

        fun getRank(): Int? {
            return getPref().getInt(Constants.RANK, 0)
        }

        fun deletePerefrences() {
            getPref().edit().clear().apply()
        }
    }
}