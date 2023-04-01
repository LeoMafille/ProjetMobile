package com.example.application.types

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="seances")
data class Seance(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val date:Date,
    val eleves:List<Eleve>,
    val supprime:Boolean
)