package com.fadybassem.elabda3task.data.remote

import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/users/JakeWharton/repos")
    fun gatApiData(@Query("page") page: Int, @Query("per_page") per_page: Int): Call<List<DataModel>>

}