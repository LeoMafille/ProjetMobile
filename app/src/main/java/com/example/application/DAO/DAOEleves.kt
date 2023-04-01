package com.example.application.DAO

import androidx.room.*
import com.example.application.types.Eleve

@Dao
interface DAOEleves {
    @Query("SELECT * FROM eleves")
    fun getAllEleves(): List<Eleve>

    fun getEleveById(id: Int) : Eleve{
        return getAllEleves().first(){ it.id == id}
    }

    fun getElevesByFormationId(id: Int) : List<Eleve> {
        return getAllEleves().filter { it.idFormation == id }
    }

    @Insert
    fun insertEleve(eleve: Eleve)

    @Update
    fun updateEleve(eleve: Eleve)

    @Delete
    fun deleteEleve(eleve: Eleve)
}