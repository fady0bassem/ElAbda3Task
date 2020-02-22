package com.fadybassem.elabda3task.ui.activities.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fadybassem.elabda3task.data.remote.ApiClient
import com.fadybassem.elabda3task.data.remote.ApiInterface
import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import com.fadybassem.elabda3task.utils.ErrorCodes
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var dataList: ArrayList<DataModel> = ArrayList()
    val mutableDataList: MutableLiveData<List<DataModel>> = MutableLiveData()
    val loadMoreMutableData: MutableLiveData<Boolean> = MutableLiveData()
    val errorMutableData: MutableLiveData<String> = MutableLiveData()
    val reloadMutableData: MutableLiveData<Boolean> = MutableLiveData()

    fun getDataList(page: Int) {

        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.gatApiData(page, 15)

        call.enqueue(object : retrofit2.Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>
            ) {
                if (response.isSuccessful) {
                    try {
                        val jsonResponse = response.body()

                        if (jsonResponse?.isEmpty()!!)
                            loadMoreMutableData.postValue(false)
                        else
                            loadMoreMutableData.postValue(true)

                        mutableDataList.postValue(jsonResponse)
                    } catch (exception: Exception) {
                        errorMutableData.postValue(exception.localizedMessage)
                    }
                } else {
                    errorMutableData.postValue(ErrorCodes.ERR_RESPONSE_ERROR)
                }

            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                if (t is IOException) {
                    errorMutableData.postValue(ErrorCodes.ERR_NETWORK_FAILURE)
                } else {
                    errorMutableData.postValue(ErrorCodes.ERR_CONVERSION_ISSUE)
                }
            }
        })
    }

    fun reload(){
        reloadMutableData.postValue(true)
    }
}