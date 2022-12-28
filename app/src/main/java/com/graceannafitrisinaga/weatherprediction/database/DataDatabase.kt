package com.graceannafitrisinaga.weatherprediction.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//class database untuk mengakses class DataItem
@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {

    companion object{
        private var databaseInstance : DataDatabase? = null

        //fungsi untuk mengakses database
        fun getDatabaseInstance(context: Context) : DataDatabase{
            if (databaseInstance != null){
                return databaseInstance as DataDatabase
            }else{
                databaseInstance = Room.databaseBuilder(context, DataDatabase::class.java, "data_database")
                    .build()

                return databaseInstance as DataDatabase
            }
        }
    }

    //memanggil file DataDao agar dapat mengakses entity
    abstract fun getDataDao() : DataDao
}