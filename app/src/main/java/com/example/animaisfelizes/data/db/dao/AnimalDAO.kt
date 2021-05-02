package com.example.animaisfelizes.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.animaisfelizes.data.db.entity.AnimalEntity

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
    fun getAll(): LiveData<List<AnimalEntity>>
}