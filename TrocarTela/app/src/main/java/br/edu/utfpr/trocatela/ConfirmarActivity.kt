package br.edu.utfpr.trocatela

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class ConfirmarActivity : AppCompatActivity() {

    private lateinit var etCodConfirmar : TextView
    private lateinit var etQtdConfirmar : TextView
    private lateinit var etValorConfirmar : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar)

        etCodConfirmar = findViewById(R.id.etCodConfirmar)
        etQtdConfirmar = findViewById(R.id.etQtdConfirmar)
        etValorConfirmar = findViewById(R.id.etValorConfirmar)

        etCodConfirmar.setText(intent.getStringExtra("cod"))
        etQtdConfirmar.setText(intent.getStringExtra("qtd"))
        etValorConfirmar.setText(intent.getStringExtra("valor"))
    }

    fun btConfirmarOnClick(view: View) {
        val msg = "Cod: ${etCodConfirmar.text} Qtd: ${etQtdConfirmar} Valor: ${etValorConfirmar}"
        val destinatario = "sms:+5549984221527"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(destinatario))
        intent.putExtra("sms_body", msg)

        startActivity(intent)
    }
}