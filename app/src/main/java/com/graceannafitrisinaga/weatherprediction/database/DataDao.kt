package com.graceannafitrisinaga.weatherprediction.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(dataItem: DataItem)

    @Query("select * from data_table order by id asc")
    fun readAllData() : LiveData<List<DataItem>>

    @Delete
    suspend fun deleteData(dataItem: DataItem)
}