package com.quadrado.testeapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val nome = findViewById<EditText>(R.id.et_nome)
        val telefone= findViewById<EditText>(R.id.et_telefone)
        val email = findViewById<EditText>(R.id.et_email)
        val cep = findViewById<EditText>(R.id.et_cep)
        val rua = findViewById<EditText>(R.id.et_rua)
        val bairro = findViewById<EditText>(R.id.main)
        val cidade = findViewById<EditText>(R.id.et_cidade)
        val estado= findViewById<EditText>(R.id.et_estado)
        val botaoBuscar = findViewById<ImageButton>(R.id.imgb_buscar)
        val botaoSalvar = findViewById<Button>(R.id.btn_salvar)


    }
}