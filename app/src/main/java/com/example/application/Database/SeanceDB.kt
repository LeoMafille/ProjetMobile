package com.example.application.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.application.DAO.DAOSeance
import com.example.application.types.Seance

@Database(entities = [Seance::class], version=1)
abstract class SeanceDB : RoomDatabase() {
    abstract fun daoSeance() : DAOSeance
}