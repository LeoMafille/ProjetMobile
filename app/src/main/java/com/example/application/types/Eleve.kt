package com.example.application.types

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "eleves")
data class Eleve(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nom:String,
    val idFormation:Int,
    val numTelephone:String,
    val supprime:Boolean
)