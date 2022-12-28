package com.graceannafitrisinaga.weatherprediction.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.graceannafitrisinaga.weatherprediction.database.DataDao
import com.graceannafitrisinaga.weatherprediction.database.DataItem
import com.graceannafitrisinaga.weatherprediction.retrofit.model.ResponceItem
import com.graceannafitrisinaga.weatherprediction.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val dataDao : DataDao) {

    val allData : LiveData<List<DataItem>> = dataDao.readAllData()

    suspend fun addData(data: DataItem){
        dataDao.addData(data)
    }

    suspend fun deleteData(data: DataItem){
        dataDao.deleteData(data)
    }
}