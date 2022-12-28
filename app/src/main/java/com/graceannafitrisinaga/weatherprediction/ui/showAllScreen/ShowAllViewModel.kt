package com.graceannafitrisinaga.weatherprediction.ui.showAllScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graceannafitrisinaga.weatherprediction.database.DataDatabase
import com.graceannafitrisinaga.weatherprediction.database.DataItem
import com.graceannafitrisinaga.weatherprediction.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowAllViewModel: ViewModel() {
    private lateinit var repository: Repository

    //fungsi untuk menampilkan data dari database menggunakan livedata jika online
    fun getAllData(context: Context): LiveData<List<DataItem>> {
        //variabel untuk mengambil data dari repository jika offline
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)
        return repository.allData
    }

    //fungsi untuk menghapus data dari repository dan database
    fun deleteData(context: Context, dataItem: DataItem){
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(dataItem)
        }
    }
}