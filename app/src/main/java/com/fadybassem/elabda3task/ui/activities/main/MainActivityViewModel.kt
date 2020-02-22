package com.fadybassem.elabda3task.ui.activities.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fadybassem.elabda3task.data.remote.ApiClient
import com.fadybassem.elabda3task.data.remote.ApiInterface
import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var dataList: ArrayList<DataModel> = ArrayList()
    val mutableDataList: MutableLiveData<List<DataModel>> = MutableLiveData()
    val loadMore: MutableLiveData<Boolean> = MutableLiveData()

    fun getDataList(page: Int) {

        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.gatApiData(page, 10)

        call.enqueue(object : retrofit2.Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                val jsonResponse = response.body()

                if (jsonResponse?.isEmpty()!!)
                    loadMore.postValue(false)
                else
                    loadMore.postValue(true)

                mutableDataList.postValue(jsonResponse)
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                Log.d("ERROR : ", t?.localizedMessage.toString())
            }
        })
    }

}