package com.quadrado.testeapi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDAO {

    @Insert
    fun salvar(c: Usuario)

    @Query("SELECT * FROM Usuario ORDER BY id")
    fun listarContatos(): List<Usuario>

}