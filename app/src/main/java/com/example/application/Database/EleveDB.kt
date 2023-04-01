package com.example.application.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.application.DAO.DAOEleves
import com.example.application.types.Eleve

@Database(entities = [Eleve::class], version=1)
abstract class EleveDB : RoomDatabase() {
    abstract fun daoEleves() : DAOEleves
}