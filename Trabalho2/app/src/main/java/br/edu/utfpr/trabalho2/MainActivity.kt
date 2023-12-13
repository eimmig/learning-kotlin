package br.edu.utfpr.trabalho2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.edu.utfpr.trabalho2.database.DatabaseHandler
import br.edu.utfpr.trabalho2.entity.Coordenada

class MainActivity : AppCompatActivity() {

    private lateinit var etCod: EditText
    private lateinit var etDescricao: EditText
    private lateinit var etLatitude: EditText
    private lateinit var etLongitude: EditText


    private lateinit var banco: DatabaseHandler

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById(R.id.etCod)
        etDescricao = findViewById(R.id.etDescricao)
        etLatitude = findViewById(R.id.etLatitude)
        etLongitude = findViewById(R.id.etLongitude)

        val btExcluir = findViewById<Button>(R.id.btExcluir)

        if (intent.getIntExtra("cod", 0) != 0) {
            val cod = intent.getIntExtra("cod", 0)
            val descricao = intent.getStringExtra("descricao")
            val latitude = intent.getStringExtra("latitude")
            val longitude = intent.getStringExtra("longitude")

            etCod.setText(cod.toString())
            etDescricao.setText(descricao)
            etLatitude.setText(latitude)
            etLongitude.setText(longitude)

            // Deixa os campos readonly
            etCod.isEnabled = false
            etLatitude.isEnabled = false
            etLongitude.isEnabled = false
            btExcluir.visibility = View.VISIBLE
        } else {
            btExcluir.visibility = View.GONE
            etCod.isEnabled = false
        }

        banco = DatabaseHandler(this)

        if (intent.getIntExtra("cod", 0) == 0) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            } else {
                obterLocalizacaoAtual()
            }
        }
    }

    private fun obterLocalizacaoAtual() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                atualizarCamposLocalizacao(location)
            } else {
                Toast.makeText(this, "Ative o GPS para obter a localização", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun atualizarCamposLocalizacao(location: Location?) {
        if (location != null) {
            etLatitude.setText(location.latitude.toString())
            etLongitude.setText(location.longitude.toString())
        }
    }

    fun btAlterarOnClick(view: View) {
        if (etDescricao.text.toString().isEmpty() || etLatitude.text.toString().isEmpty() || etLongitude.text.toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (etCod.text.toString().isEmpty()) {
            val coordenada = Coordenada(
                0,
                etDescricao.text.toString(),
                etLatitude.text.toString(),
                etLongitude.text.toString()
            )
            banco.insert(coordenada)
            Toast.makeText(this, "Inclusão realizada com sucesso", Toast.LENGTH_SHORT).show()
            limparTela()
        } else {
            val coordenada = Coordenada(
                etCod.text.toString().toInt(),
                etDescricao.text.toString(),
                etLatitude.text.toString(),
                etLongitude.text.toString()
            )
            banco.update(coordenada)
            Toast.makeText(this, "Alteração realizada com sucesso", Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    fun btExcluirOnClick(view: View) {
        banco.delete(etCod.text.toString().toInt())
        Toast.makeText(this, "Exclusão realizada com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun limparTela() {
        etCod.setText("")
        etDescricao.setText("")
        etLatitude.setText("")
        etLongitude.setText("")
    }
}
