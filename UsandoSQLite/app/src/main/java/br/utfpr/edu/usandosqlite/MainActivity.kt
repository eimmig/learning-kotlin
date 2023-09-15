package br.utfpr.edu.usandosqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText
    private lateinit var banco : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)

        banco = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath("dbfile.sqlite"),null)
        banco.execSQL("CREATE TABLE IF NOT EXISTS pessoa (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT)")
    }

    fun btListarOnClick(view: View) {
        val cursor = banco.query("pessoa", null, null, null, null, null, null)
        val saida = StringBuilder()

        while (cursor.moveToNext()) {
            saida.append(cursor.getString(1))
            saida.append("-")
            saida.append(cursor.getString(2))
            saida.append("\n")
        }

        Toast.makeText(this, saida, Toast.LENGTH_SHORT).show()
    }
    fun btPesquisarOnClick(view: View) {
        val cod = etCod.text.toString().toInt();
        val cursor = banco.query("pessoa", null, "_id=${cod}", null, null, null, null)
        if (cursor.moveToNext()) {
            etNome.setText(cursor.getString(1))
            etTelefone.setText(cursor.getString(2))
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_SHORT).show()
        }
    }
    fun btExcluirOnClick(view: View) {
        val cod = etCod.text.toString().toInt();
        banco.delete("pessoa", "_id=${cod}", null)
        Toast.makeText(this,"Exclusão realizada com sucesso", Toast.LENGTH_SHORT).show()
        limparTela()
    }
    fun btAlterarOnClick(view: View) {
        val registro = ContentValues()
        val cod = etCod.text.toString().toInt();
        registro.put("nome", etNome.text.toString())
        registro.put("telefone", etTelefone.text.toString())
        banco.update("pessoa", registro, "_id=${cod}", null)

        Toast.makeText(this,"Alteração realizada com sucesso", Toast.LENGTH_SHORT).show()
    }
    fun btIncluirOnClick(view: View) {
        val registro = ContentValues()

        registro.put("nome", etNome.text.toString())
        registro.put("telefone", etTelefone.text.toString())
        banco.insert("pessoa", null, registro)

        Toast.makeText(this,"Inclusão realizada com sucesso", Toast.LENGTH_SHORT).show()

        limparTela()
    }

    fun limparTela () {
        etCod.setText("")
        etNome.setText("")
        etTelefone.setText("")
    }
}