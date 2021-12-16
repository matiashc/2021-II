package co.edu.ufps.a2021_ii.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("CREATE TABLE libros(id text primary key, titulo text, descripcion text, isbn text, imagen text)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}