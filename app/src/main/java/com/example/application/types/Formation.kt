package com.example.application.types

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="formations")
data class Formation (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //TODO: Les autres éléments de la bdd

    val supprime:Boolean
)