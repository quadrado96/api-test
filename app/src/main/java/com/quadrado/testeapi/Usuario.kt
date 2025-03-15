package com.quadrado.testeapi

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val nome: String,
    val telefone: String,
    val email: String,
    val cep: String,
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String
) {

    override fun toString(): String {
        return "$id - $nome    $telefone" +
                "$email   $cep" +
                "$rua, $bairro / $cidade - $estado"
    }

}
