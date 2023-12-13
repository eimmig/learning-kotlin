package br.edu.utfpr.trabalho2.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.edu.utfpr.trabalho2.entity.Coordenada

class DatabaseHandler ( context : Context ) : SQLiteOpenHelper ( context, DATABASE_NAME, null, DATABASE_VERSION ) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "dbcoordenadas.sqlite"
        private val TABLE_NAME = "coodenadas"
        private val KEY_ID = "_id"
        private val KEY_DESCRICAO = "descricao"
        private val KEY_LATITUDE = "latitude"
        private val KEY_LONGITUDE = "longitude"
    }


    override fun onCreate(bd: SQLiteDatabase?) {
        bd?.execSQL( "CREATE TABLE IF NOT EXISTS $TABLE_NAME ( $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_DESCRICAO TEXT, $KEY_LATITUDE TEXT, $KEY_LONGITUDE TEXT)" )
    }

    override fun onUpgrade(bd: SQLiteDatabase?, p1: Int, p2: Int) {
        bd?.execSQL( "DROP TABLE $TABLE_NAME" )
        onCreate( bd )
    }

    fun insert(coordenada: Coordenada) {
        val registro = ContentValues()
        registro.put( KEY_DESCRICAO, coordenada.descricao )
        registro.put( KEY_LATITUDE, coordenada.latitude )
        registro.put( KEY_LONGITUDE, coordenada.longitude )

        val bd = this.writableDatabase
        bd.insert( TABLE_NAME, null, registro )
    }

    fun update(coordenada: Coordenada) {
        val registro = ContentValues()
        registro.put( KEY_DESCRICAO, coordenada.descricao )

        val bd = this.writableDatabase
        bd.update( TABLE_NAME, registro, "_id=${coordenada._id}", null )
    }

    fun delete( _id : Int) {
        val bd = this.writableDatabase
        bd.delete( TABLE_NAME, "_id=${_id}", null )
    }

    fun find( _id : Int) : Coordenada? {
        val bd = this.writableDatabase
        val cursor = bd.query( TABLE_NAME,
            null,
            "_id=${_id}",
            null,
            null,
            null,
            null
        )

        return if (cursor.moveToNext() ) {
            val coordenada = Coordenada( _id, cursor.getString( 1 ), cursor.getString( 2 ), cursor.getString( 3 ) )
            coordenada
        } else {
            null
        }
    }

    fun list() : MutableList<Coordenada> {
        val bd = this.writableDatabase

        val cursor = bd.query( TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val registros = mutableListOf<Coordenada>()

        while ( cursor.moveToNext() ) {
            val coordenada = Coordenada( cursor.getInt( 0 ), cursor.getString( 1 ), cursor.getString( 2 ), cursor.getString( 3 ) )
            registros.add( coordenada )
        }

        return registros
    }

    fun listCursor(): Cursor {
        val bd = this.writableDatabase

        return bd.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}