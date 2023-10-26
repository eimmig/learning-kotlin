package br.utfpr.edu.prova_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class pesquisaActivity : AppCompatActivity() {

    private lateinit var pesquisaCombustivel : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)

        pesquisaCombustivel = findViewById(R.id.pesquisaCombustivel)

        pesquisaCombustivel.setOnItemClickListener { adapterView, view, i, l ->
            val cod = l.inc().toString()
            Intent().apply {
                putExtra( "codRetorno", cod )
                setResult( RESULT_OK, this )
            }
            finish()
        }
    }
}