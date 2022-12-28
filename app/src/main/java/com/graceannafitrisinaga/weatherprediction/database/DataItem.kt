package com.graceannafitrisinaga.weatherprediction.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//pembuatan entity pada tabel di database
@Entity(tableName = "data_table")
class DataItem (
    @PrimaryKey(autoGenerate = true)
    //variabel id dijadikan sebagai primary key
    val id: Int,
    //merepresentasikan field
    val firstName: String,
    val lastName: String,
    val email:String,
    val rollNum:String,
    val regNum:String,
    val branch: String,
    val year:String
)