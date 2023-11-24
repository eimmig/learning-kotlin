package br.edu.utfpr.usandopreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

private const val PREFERENCE = "PREFERENCE_NAME"

class MainActivity : AppCompatActivity() {

    private lateinit var ivEstrela : ImageView
    private lateinit var sharedPreferences : SharedPreferences

    var ligado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivEstrela = findViewById( R.id.ivEstrela )
        sharedPreferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE)

        ligado = lerEstado()

        alteraEstadoEstrela(ligado)
    }

    private fun alteraEstadoEstrela(ligado: Boolean) {
        when (ligado) {
            true -> ivEstrela.setImageResource(android.R.drawable.star_big_on)
            false -> ivEstrela.setImageResource(android.R.drawable.star_big_off)
        }
    }

    private fun lerEstado(): Boolean {
        return sharedPreferences.getBoolean("ligado", false)
    }

    fun btOnOffOnClick(view: View) {

        alteraEstadoEstrela(ligado)

        ligado = !ligado

        salvarEstado(ligado)
    }

    private fun salvarEstado(ligado: Boolean) {
        var editor = sharedPreferences.edit()
        editor.putBoolean("ligado", ligado)
        editor.commit()
    }

    fun btPreferenceOnClick(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}