package com.example.application.DAO

import androidx.room.*
import com.example.application.types.Seance

@Dao
interface DAOSeance {
    @Query("SELECT * FROM seances")
    fun getAllSeances(): List<Seance>

    fun getSeancesById(id: Int) : Seance{
        return getAllSeances().first(){ it.id == id}
    }

    @Insert
    fun insertSeance(seance: Seance)

    @Update
    fun updateSeance(seance: Seance)

    @Delete
    fun deleteSeance(seance: Seance)
}