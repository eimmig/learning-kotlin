package com.example.calculaimckotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado: TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etAltura = findViewById(R.id.etAltura)
        etPeso = findViewById(R.id.etPeso)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            btCalcularOnclick()
        }

        btLimpar.setOnClickListener {
            btLimparOnclick()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, "Botão que Limpa", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener false
        }
    }

    private fun btCalcularOnclick() {

    }

    private fun btLimparOnclick() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.text = "0.0"
        etPeso.requestFocus()
    }
}