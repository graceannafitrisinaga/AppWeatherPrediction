package com.graceannafitrisinaga.weatherprediction.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//kelas yang berisi methods yang digunakan untuk mengakses database
@Dao
interface DataDao {

    //menggunakan metode insert untuk menjalankan fungsi addData pada file DataItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(dataItem: DataItem)

    ////menggunakan query SQL untuk menampilkan data
    @Query("select * from data_table order by id asc")
    fun readAllData() : LiveData<List<DataItem>>

    ////menggunakan metode delete untuk menjalankan fungsi deleteData pada file DataItem
    @Delete
    suspend fun deleteData(dataItem: DataItem)
}