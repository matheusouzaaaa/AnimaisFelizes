package com.example.animaisfelizes.repository

import com.example.animaisfelizes.data.db.entity.AnimalEntity

interface AnimalRepository {
    // Inserir um animal
    suspend fun insertAnimal(nome: String, idade: String, proprietario: String): Long
    // Editar um animal
    suspend fun updateAnimal(id: Long, nome: String, idade: String, proprietario: String)
    // Deletar um animal
    suspend fun deleteAnimal(id: Long)
    // Deletar todos os animais
    suspend fun deleteAllAnimais()
    // Listar todos os animais
    suspend fun getAllAnimais(): List<AnimalEntity>
}