package com.example.application.Database

import androidx.room.Database
import com.example.application.DAO.DAOFormation
import com.example.application.types.Formation

@Database(entities = [Formation::class], version=1)
abstract class FormationDB {
    abstract fun daeFormation() : DAOFormation
}