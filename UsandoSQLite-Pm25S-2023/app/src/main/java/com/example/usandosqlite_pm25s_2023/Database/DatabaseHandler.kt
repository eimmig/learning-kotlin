package com.example.usandosqlite_pm25s_2023.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.usandosqlite_pm25s_2023.Entity.Pessoa
import java.lang.StringBuilder

class DatabaseHandler (context : Context) : SQLiteOpenHelper ( context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "dbfile.sqlite"
        private val TABLE_NAME = "pessoa"
        private val KEY_ID = "_id"
        private val KEY_NOME = "nome"
        private val KEY_TELEFONE = "telefone"
    }

    override fun onCreate(bd: SQLiteDatabase?) {
        bd?.execSQL( "CREATE TABLE IF NOT EXISTS ${TABLE_NAME} (${KEY_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${KEY_NOME} TEXT, ${KEY_TELEFONE} TEXT)" )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE ${TABLE_NAME}")
        onCreate(db)
    }

    fun insert (pessoa : Pessoa) {
        val registro = ContentValues()
        registro.put(KEY_NOME, pessoa.nome)
        registro.put(KEY_TELEFONE, pessoa.telefone)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, registro)
    }

    fun update (pessoa : Pessoa) {
        val registro = ContentValues()
        registro.put(KEY_NOME, pessoa.nome)
        registro.put(KEY_TELEFONE, pessoa.telefone)

        val db = this.writableDatabase
        db.update(TABLE_NAME, registro, "_id=${pessoa._id}", null)
    }

    fun delete (_id : Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"_id=${_id}", null)
    }

    fun find (_id : Int) : Pessoa? {
        val db = this.writableDatabase
        val cursor = db.query(TABLE_NAME, null, "_id=${_id}", null, null, null, null)

        if (cursor.moveToNext()) {
            return Pessoa(_id, cursor.getString(1), cursor.getString(2));
        } else {
            return null;
        }
    }

    fun list() : String {
        val db = this.writableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        val saida = StringBuilder()
        while ( cursor.moveToNext() ) {
            saida.append( cursor.getString( 1 ) )
            saida.append( " - ")
            saida.append( cursor.getString( 2 ) )
            saida.append( "\n")
        }
        return saida.toString();
    }
}