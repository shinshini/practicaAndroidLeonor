package com.example.myapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLiteOpenHelper(
    context:    Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context,name,factory,version){
    override fun onCreate(db: SQLiteDatabase?) {
       db?.execSQL(
           "CREATE TABLE productos("+
                   "id_productos INTEGER PRIMARY KEY,"+
                   "nombre TEXT,"+
                   "precio REAL)"
       )

        db?.execSQL(
            "CREATE TABLE categoria("+
              "id_categoria INTERGER PRIMARY KEY,"+
              "nombre TEXT,"+
            "descripcion TEXT)"
        )
        db?.execSQL(
            "CREATE TABLE proveedor("+
                    "id_proveedor INTEGER PRIMARY KEY,"+
                    "nombre TEXT,"+
                    "nit INTEGER,"+
                    "direccion TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS productos")
        onCreate(db)

        db?.execSQL("DROP TABLE IF EXISTS categoria")
        onCreate(db)

        db?.execSQL("DROP TABLE IF EXISTS proveedor")
        onCreate(db)
    }


}