package br.edu.utfpr.trabalho2.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import br.edu.utfpr.trabalho2.MainActivity
import br.edu.utfpr.trabalho2.R
import br.edu.utfpr.trabalho2.entity.Coordenada

private const val COD = 0
private const val DESCRICAO = 1
private const val LATITUDE = 2
private const val LONGITUDE = 3

class MeuAdapter (val context: Context, val cursor : Cursor ) : BaseAdapter() {
    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(id: Int): Any {
        cursor.moveToPosition( id )
        val coordenada = Coordenada(cursor.getInt(COD), cursor.getString(DESCRICAO), cursor.getString(
            LATITUDE
        ), cursor.getString(
            LONGITUDE
        ))
        return coordenada
    }

    override fun getItemId(id: Int): Long {
        cursor.moveToPosition( id )
        return cursor.getInt(COD).toLong()
    }

    override fun getView(id: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.elemento_lista, null)

        val tvDescricao = v.findViewById<TextView>(R.id.tvDescricao)
        val tvLatitude = v.findViewById<TextView>(R.id.tvLatitude)
        val tvLongitude = v.findViewById<TextView>(R.id.tvLongitude)
        val btEditarElementoLista = v.findViewById<ImageButton>(R.id.btEditarElementoLista)

        cursor.moveToPosition(id)

        tvDescricao.text = cursor.getString(DESCRICAO)
        tvLatitude.text = cursor.getString(LATITUDE)
        tvLongitude.text = cursor.getString(LONGITUDE)

        btEditarElementoLista.setOnClickListener {
            cursor.moveToPosition(id)
            val intent = Intent( context, MainActivity::class.java )
            intent.putExtra( "cod", cursor.getInt(COD))
            intent.putExtra( "descricao", cursor.getString(DESCRICAO))
            intent.putExtra( "latitude", cursor.getString(LATITUDE))
            intent.putExtra( "longitude", cursor.getString(LONGITUDE))
            context.startActivity( intent )
        }
        return v
    }
}