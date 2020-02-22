package com.fadybassem.elabda3task.ui.activities.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fadybassem.elabda3task.data.PreferencesHelper
import com.fadybassem.elabda3task.data.remote.ApiClient
import com.fadybassem.elabda3task.data.remote.ApiInterface
import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import com.fadybassem.elabda3task.data.room.DatabaseService
import com.fadybassem.elabda3task.data.room.Table
import com.fadybassem.elabda3task.utils.ErrorCodes
import com.fadybassem.elabda3task.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var dataList: ArrayList<DataModel> = ArrayList()
    val mutableDataList: MutableLiveData<List<Table>> = MutableLiveData()
    val loadMoreMutableData: MutableLiveData<Boolean> = MutableLiveData()
    val errorMutableData: MutableLiveData<String> = MutableLiveData()
    val reloadMutableData: MutableLiveData<Boolean> = MutableLiveData()

    var database: DatabaseService = DatabaseService(getApplication())

    var firstTime = true

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

                        saveDB(jsonResponse!!)
                    } catch (exception: Exception) {
                        errorMutableData.postValue(exception.localizedMessage)
                    }
                } else {
                    errorMutableData.postValue(ErrorCodes.ERR_RESPONSE_ERROR)
                }
                getDB()
            }

            override fun onFailure(call: Call<List<DataModel>>?, t: Throwable?) {
                if (getDB()?.size == 0 || getDB() == null) {
                    if (t is IOException) {
                        errorMutableData.postValue(ErrorCodes.ERR_NETWORK_FAILURE)
                    } else {
                        errorMutableData.postValue(ErrorCodes.ERR_CONVERSION_ISSUE)
                    }
                }
            }
        })
    }

    fun reload() {
        firstTime = true
        reloadMutableData.postValue(true)
    }

    private fun saveDB(jsonResponse: List<DataModel>) {
        var rank: Int? = PreferencesHelper.getRank()

        for (item: DataModel in jsonResponse) {
            rank = rank?.plus(1)
            val table = Table(
                item.id,
                item.owner.avatarUrl,
                item.name,
                item.fullName,
                item.size,
                item.forks,
                item.watchersCount,
                rank!!
            )
            database.insert(table)
        }
        PreferencesHelper.setRank(rank!!)
    }

    private fun getDB(): List<Table?>? {
        val tables: List<Table?>? = database.getAll()
        val network = NetworkUtils.isNetworkConnected(getApplication())
        if (!network) {
            if (firstTime) {
                firstTime = false
                mutableDataList.postValue((tables as List<Table>?))
            }
        } else {
            mutableDataList.postValue((tables as List<Table>?))
        }

        if (tables?.isEmpty()!!)
            loadMoreMutableData.postValue(false)
        else
            loadMoreMutableData.postValue(true)

        return tables
    }
}