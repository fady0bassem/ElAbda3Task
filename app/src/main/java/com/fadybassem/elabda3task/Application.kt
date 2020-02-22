package com.fadybassem.elabda3task

import android.app.Application
import android.content.Context

class Application : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context: Context =
            applicationContext()
    }
}