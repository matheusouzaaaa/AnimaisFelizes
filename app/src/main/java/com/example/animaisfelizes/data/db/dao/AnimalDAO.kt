package com.example.animaisfelizes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.animaisfelizes.data.db.entity.AnimalEntity
/*
    -> Adicionado a anotação Dao para importar a biblioteca do Room.
    -> Após isso as operações que vão ser feitas no banco de dados.
    -> Utilizado as anotações em cada função, com suspend, para usar com o Kotlin Coroutines.
 */

@Dao
interface AnimalDAO {
    @Insert
    suspend fun insert(animal: AnimalEntity): Long

    @Update
    suspend fun update(animal: AnimalEntity)

    @Query("DELETE FROM animal WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM animal")
    suspend fun deleteAll()

    @Query("SELECT * FROM animal")
    suspend fun getAll(): List<AnimalEntity>
}