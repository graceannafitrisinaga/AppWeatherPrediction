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

    //menampilkan data menggunakan LiveData dengan menjalankan fungsi readAllData()
    //yang ada pada file dataDao
    val allData : LiveData<List<DataItem>> = dataDao.readAllData()

    //fungsi untuk menambahkan data pada DataItem menggunakan class dataDao
    suspend fun addData(data: DataItem){
        dataDao.addData(data)
    }

    //fungsi untuk menghapus data pada DataItem menggunakan class dataDao
    suspend fun deleteData(data: DataItem){
        dataDao.deleteData(data)
    }
}