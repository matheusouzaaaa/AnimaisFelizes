package com.example.animaisfelizes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
data class AnimalEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val idade: String,
    val proprietario: String
)