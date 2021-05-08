package com.example.animaisfelizes.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
    -> Adicionado a anotação Entity para transformar essa entidade em uma entidade do Room.
    -> Anotação Parcelize e Parcelable serve para passar o argumento para uma outra tela, pelo fragments.
 */
@Parcelize
@Entity(tableName = "animal")
data class AnimalEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val idade: String,
    val proprietario: String
) : Parcelable