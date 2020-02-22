package com.fadybassem.elabda3task.data.remote

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(context: Context) {
    companion object {

        val baseURL: String = "https://api.github.com/"
        var retofit: Retrofit? = null
        var mcontext: Context? = null

        val client: Retrofit
            get() {

                if (retofit == null) {
                    retofit = Retrofit.Builder()
                        .baseUrl(baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retofit!!
            }
    }

    init {
        mcontext = context.applicationContext
    }
}