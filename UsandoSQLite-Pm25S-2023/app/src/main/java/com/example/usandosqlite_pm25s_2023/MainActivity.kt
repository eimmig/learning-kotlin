package com.example.usandosqlite_pm25s_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.usandosqlite_pm25s_2023.Database.DatabaseHandler
import com.example.usandosqlite_pm25s_2023.Entity.Pessoa

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById( R.id.etCod )
        etNome = findViewById( R.id.etNome )
        etTelefone = findViewById( R.id.etTelefone )

        banco = DatabaseHandler(this)
    }

    fun btIncluirOnClick(view: View) {

        val pessoa = Pessoa (0, etNome.text.toString(), etTelefone.text.toString())
        banco.insert(pessoa)

        Toast.makeText( this, "Inclusão realizada com sucesso", Toast.LENGTH_SHORT ).show()

        limparTela()
    }
    fun btAlterarOnClick(view: View) {
        val pessoa = Pessoa (etCod.text.toString().toInt(), etNome.text.toString(), etTelefone.text.toString())
        banco.update(pessoa)

        Toast.makeText( this, "Alteração realizada com sucesso", Toast.LENGTH_SHORT ).show()
    }
    fun btExcluirOnClick(view: View) {
        val cod = etCod.text.toString().toInt()

        banco.delete(cod)
        Toast.makeText( this, "Exclusão realizada com sucesso", Toast.LENGTH_SHORT ).show()
    }
    fun btPesquisarOnClick(view: View) {
        val cod = etCod.text.toString().toInt()

        val pessoa = banco.find(cod)

        if (pessoa != null) {
            etNome.setText(pessoa.nome)
            etTelefone.setText(pessoa.telefone)
        } else {
            Toast.makeText( this, "Registro não encontrado", Toast.LENGTH_SHORT ).show()
        }
    }
    fun btListarOnClick(view: View) {

        val saida = banco.list()

        Toast.makeText( this, saida, Toast.LENGTH_SHORT ).show()
    }

    fun limparTela() {
        etCod.setText( "" )
        etNome.setText( "" )
        etTelefone.setText( "" )
    }
}