package com.example.usandosqlite_pm25s_2023.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION_CODES.P
import com.example.usandosqlite_pm25s_2023.entity.Pessoa
import java.lang.StringBuilder

class DatabaseHandler ( context : Context ) : SQLiteOpenHelper ( context, DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "dbfile.sqlite"
        private val TABLE_NAME = "pesso"
        private val KEY_ID = "_id"
        private val KEY_NOME = "nome"
        private val KEY_TELEFONE = "telefone"
    }


    override fun onCreate(bd: SQLiteDatabase?) {
        bd?.execSQL( "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} ( ${KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${KEY_NOME} TEXT, ${KEY_TELEFONE} TEXT)" )
    }

    override fun onUpgrade(bd: SQLiteDatabase?, p1: Int, p2: Int) {
        bd?.execSQL( "DROP TABLE ${TABLE_NAME}" )
        onCreate( bd )
    }

    fun insert( pessoa : Pessoa) {
        val registro = ContentValues()
        registro.put( KEY_NOME, pessoa.nome )
        registro.put( KEY_TELEFONE, pessoa.telefone )

        val bd = this.writableDatabase
        bd.insert( TABLE_NAME, null, registro )
    }

    fun update( pessoa : Pessoa) {
        val registro = ContentValues()
        registro.put( KEY_NOME, pessoa.nome )
        registro.put( KEY_TELEFONE, pessoa.telefone )

        val bd = this.writableDatabase
        bd.update( TABLE_NAME, registro, "_id=${pessoa._id}", null )
    }

    fun delete( _id : Int) {
        val bd = this.writableDatabase
        bd.delete( TABLE_NAME, "_id=${_id}", null )
    }

    fun find( _id : Int) : Pessoa? {
        val bd = this.writableDatabase
        val cursor = bd.query( TABLE_NAME,
            null,
            "_id=${_id}",
            null,
            null,
            null,
            null
        )

        if ( cursor.moveToNext() ) {
            val pessoa = Pessoa( _id, cursor.getString( 1 ), cursor.getString( 2 ) )
            return pessoa
        } else {
            return null
        }
    }

    fun list() : MutableList<Pessoa> {
        val bd = this.writableDatabase

        val cursor = bd.query( TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val registros = mutableListOf<Pessoa>()

        while ( cursor.moveToNext() ) {
            val pessoa = Pessoa( cursor.getInt( 0 ), cursor.getString( 1 ), cursor.getString( 2 ) )
            registros.add( pessoa )
        }

        return registros
    }

    fun listCursor() : Cursor {
        val bd = this.writableDatabase

        val cursor = bd.query( TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        return cursor
    }
}