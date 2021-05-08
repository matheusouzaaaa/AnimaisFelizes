package com.example.animaisfelizes.repository

import com.example.animaisfelizes.data.db.dao.AnimalDAO
import com.example.animaisfelizes.data.db.entity.AnimalEntity
/*
    -> Tem que passar o DAO para dizer quem vai gerenciar os dados para o banco de dados.
 */

class DatabaseDataSource(private val animalDAO: AnimalDAO) : AnimalRepository {
    override suspend fun insertAnimal(nome: String, idade: String, proprietario: String): Long {
        // instancia a entidade de animais para salvar
        val animal = AnimalEntity(
            nome = nome,
            idade = idade,
            proprietario = proprietario
        )
        // retorna o id do animal inserido
        return animalDAO.insert(animal)
    }

    override suspend fun updateAnimal(id: Long, nome: String, idade: String, proprietario: String) {
        // instancia a entidade de animais para editar
        val animal = AnimalEntity(
            id = id,
            nome = nome,
            idade = idade,
            proprietario = proprietario
        )
        return animalDAO.update(animal)
    }

    override suspend fun deleteAnimal(id: Long) {
        animalDAO.delete(id)
    }

    override suspend fun deleteAllAnimais() {
        animalDAO.deleteAll()
    }

    override suspend fun getAllAnimais(): List<AnimalEntity> {
        return animalDAO.getAll()
    }

}