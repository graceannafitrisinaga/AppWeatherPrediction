package com.graceannafitrisinaga.weatherprediction.ui.addDataScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graceannafitrisinaga.weatherprediction.database.DataDatabase
import com.graceannafitrisinaga.weatherprediction.database.DataItem
import com.graceannafitrisinaga.weatherprediction.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDataViewModel : ViewModel() {

    //menginisiasi objek repository menggunakan class Repository
    private lateinit var repository: Repository

    //fungsi untuk menambahkan data kedalam database dan repository agar dapat diakses offline
    fun addData(context: Context, dataItem: DataItem){
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)

        viewModelScope.launch(Dispatchers.IO){
            repository.addData(dataItem)
        }
    }
}