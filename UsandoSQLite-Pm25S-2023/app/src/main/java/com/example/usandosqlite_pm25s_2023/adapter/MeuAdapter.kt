package com.example.usandosqlite_pm25s_2023.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.usandosqlite_pm25s_2023.MainActivity
import com.example.usandosqlite_pm25s_2023.R
import com.example.usandosqlite_pm25s_2023.entity.Pessoa


private const val COD = 0
private const val NOME = 1
private const val TELEFONE = 2

class MeuAdapter ( val context: Context, val cursor : Cursor ) : BaseAdapter() {
    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(id: Int): Any {
        cursor.moveToPosition( id )
        val pessoa = Pessoa( cursor.getInt( 0 ), cursor.getString( 1 ), cursor.getString( 2 ) )
        return pessoa
    }

    override fun getItemId(id: Int): Long {
        cursor.moveToPosition( id )
        return cursor.getInt( 0 ).toLong()
    }

    override fun getView(id: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.elemento_lista, null)

        val tvNomeElementoLista = v.findViewById<TextView>(R.id.tvNomeElementoLista)
        val tvTelefoneElementoLista = v.findViewById<TextView>(R.id.tvTelefoneElementoLista)
        val btEditarElementoLista = v.findViewById<ImageButton>(R.id.btEditarElementoLista)

        cursor.moveToPosition(id)

        tvNomeElementoLista.text = cursor.getString(1)
        tvTelefoneElementoLista.text = cursor.getString(2)

        btEditarElementoLista.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("cod", cursor.getInt(COD))
            intent.putExtra("nome", cursor.getString(NOME))
            intent.putExtra("telefone", cursor.getString(TELEFONE))

            context.startActivity(intent)
            //Toast.makeText(context, "Item ${id} pressionado", Toast.LENGTH_SHORT).show()
        }

        return v

    }




}