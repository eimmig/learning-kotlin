package br.utfpr.edu.prova_1

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etCidade : EditText
    private lateinit var etQuantidade : EditText

    private lateinit var banco : SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etCod = findViewById(R.id.etCod)
        etCidade = findViewById(R.id.etCidade)
        etQuantidade = findViewById(R.id.etQuantidade)

        banco = SQLiteDatabase.openOrCreateDatabase( this.getDatabasePath( "dbfile.sqlite" ), null )
        banco.execSQL("CREATE TABLE IF NOT EXISTS abastecimento (id INTEGER PRIMARY KEY AUTOINCREMENT, cod INTEGER, cidade_abastecimento TEXT, quantidade REAL)")

        val btExtra = findViewById<Button>(R.id.btExtra)
        btExtra.setOnClickListener {
            val intent = Intent(this, extraActivity::class.java)
            startActivity(intent)
        }
    }

    fun btPesquisarOnClick(view: View) {
        val intent = Intent( this, pesquisaActivity::class.java ).let {
            register.launch( it )
        }
    }

    val register = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() ){
            result : ActivityResult ->

        if ( result.resultCode == RESULT_OK ) {
            result.data?.let {
                if ( it.hasExtra( "codRetorno" ) ) {
                    val codRetorno = it.getStringExtra( "codRetorno" )
                    etCod.setText( codRetorno )
                }
            }
        }
    }

    fun btIncluirOnClick(view: View) {

        if (etCod.text.toString().isEmpty() || etCidade.text.toString().isEmpty() || etQuantidade.text.toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        if (etCod.text.toString().toIntOrNull()!! > 5) {
            Toast.makeText(this, "Código Inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val registro = ContentValues();
        registro.put( "cod", etCod.text.toString().toIntOrNull())
        registro.put( "cidade_abastecimento", etCidade.text.toString() )
        registro.put("quantidade", etQuantidade.text.toString().toDoubleOrNull())
        banco.insert( "abastecimento", null, registro )

        val cursor = banco.rawQuery("SELECT COUNT(*) FROM abastecimento", null)

        if (cursor.moveToFirst()) {
            val count = cursor.getInt(0)
            Toast.makeText(this, "Abastecimento Registrado. Qtd. Abastecimentos: ${count}", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        limparTela()
    }

    fun limparTela() {
        etCod.setText( "" )
        etCidade.setText( "" )
        etQuantidade.setText( "" )
    }

    fun btSomarQuantidadeOnClick(view: View) {
        val cursor = banco.rawQuery("SELECT SUM(quantidade) FROM abastecimento", null)

        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Quantidade Total: ${cursor.getDouble(0)}",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Quantidade Total: 0",Toast.LENGTH_SHORT).show()
        }
    }
}