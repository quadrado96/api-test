package com.quadrado.testeapi

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Usuario::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun UsuarioDAO(): UsuarioDAO

    companion object {

        private var database: Database? = null
        private val DATABASE = "USUARIO"

        fun getInstance(context: Context): Database {
            if (database == null) {
                database = criaBanco(context)
            }
            return database!!
        }

        private fun criaBanco(context: Context): Database? {
            return Room.databaseBuilder(
                context, Database::class.java, DATABASE)
                .allowMainThreadQueries().build()
        }
    }


}