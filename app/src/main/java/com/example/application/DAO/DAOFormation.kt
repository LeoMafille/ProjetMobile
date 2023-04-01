package com.example.application.DAO

import androidx.room.*
import com.example.application.types.Formation

@Dao
interface DAOFormation {
    @Query("SELECT * FROM formations")
    fun getAllFormations(): List<Formation>

    fun getFormationById(id: Int):Formation{
        return getAllFormations().first{ it.id == id }
    }

    @Insert
    fun insertFormation(formation: Formation)

    @Update
    fun updateFormation(formation: Formation)

    @Delete
    fun deleteFormation(formation: Formation)
}