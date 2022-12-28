package com.graceannafitrisinaga.weatherprediction.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class DataItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email:String,
    val rollNum:String,
    val regNum:String,
    val branch: String,
    val year:String
)