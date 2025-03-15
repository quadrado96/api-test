package com.quadrado.testeapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class Cadastro : AppCompatActivity() {

    private lateinit var nome: EditText
    private lateinit var telefone: EditText
    private lateinit var email: EditText
    private lateinit var cep: EditText
    private lateinit var rua: EditText
    private lateinit var bairro: EditText
    private lateinit var cidade: EditText
    private lateinit var estado: EditText
    private lateinit var botaoBuscar: ImageButton
    private lateinit var botaoSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nome = findViewById(R.id.et_nome)
        telefone = findViewById(R.id.et_telefone)
        email = findViewById(R.id.et_email)
        cep = findViewById(R.id.et_cep)
        rua = findViewById(R.id.et_rua)
        bairro = findViewById(R.id.et_bairro)
        cidade = findViewById(R.id.et_cidade)
        estado = findViewById(R.id.et_estado)
        botaoBuscar = findViewById(R.id.imgb_buscar)
        botaoSalvar = findViewById(R.id.btn_salvar)

        botaoBuscar.setOnClickListener {
            buscarCep(cep.text.toString())
        }

        botaoSalvar.setOnClickListener {
            val usuario = Usuario(
                id = null,
                nome = nome.text.toString(),
                telefone = telefone.text.toString(),
                email = email.text.toString(),
                cep = cep.text.toString(),
                rua = rua.text.toString(),
                bairro = bairro.text.toString(),
                cidade = cidade.text.toString(),
                estado = estado.text.toString()
            )

            Database.getInstance(this)!!.UsuarioDAO().salvar(usuario)

            finish()
        }
    }

    private fun buscarCep(cep: String) {
        val url = "https://viacep.com.br/ws/$cep/json/"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resposta = URL(url).readText()

                withContext(Dispatchers.Main) {
                    processarResposta(resposta)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Cadastro, "Erro ao buscar CEP", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun processarResposta(resp: String) {

        try {
            val respostaJSON = JSONObject(resp)

            if (respostaJSON.has("erro")) {
                Toast.makeText(this, "CEP inválido", Toast.LENGTH_LONG).show()
            } else {
                rua.setText(respostaJSON.getString("logradouro"))
                bairro.setText(respostaJSON.getString("bairro"))
                cidade.setText(respostaJSON.getString("localidade"))
                estado.setText(respostaJSON.getString("uf"))
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao processar resposta", Toast.LENGTH_LONG).show()
        }
    }
}
