package com.example.animaisfelizes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animaisfelizes.data.db.dao.AnimalDAO
import com.example.animaisfelizes.data.db.entity.AnimalEntity

/*
    -> Utilizada a anotação Database para dizer que essa classe irá obter uma instancia do banco de dados. É um singleton
 */

@Database(entities = [AnimalEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    // implementa a variável animalDAO e retornar um AnimalDAO quando chamar
    abstract val animalDAO: AnimalDAO

    // garante que vai ter uma instancia que vai guardar o contexto. assim não precisa instanciar toda hora.
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}