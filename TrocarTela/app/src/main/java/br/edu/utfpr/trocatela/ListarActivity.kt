package br.edu.utfpr.trocatela

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class ListarActivity : AppCompatActivity() {
    private lateinit var lvProdutos : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvProdutos = findViewById(R.id.lvProdutos)

        lvProdutos.setOnItemClickListener( AdapterView.OnItemClickListener { adapterView, view, pos, l ->
            val produto = (pos+1).toString()
            Intent().apply {
                putExtra("codResult", produto)
                setResult(RESULT_OK, this)
            }
            finish()
        })
    }
}