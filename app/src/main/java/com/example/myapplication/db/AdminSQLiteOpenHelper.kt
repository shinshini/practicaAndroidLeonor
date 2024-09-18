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
                   "precio REAL,"+
                   "id_proveedor INTEGER , " +  // Definimos las claves foráneas
                   "id_categoria INTEGER , " +
                   "FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor), " +
                   "FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria))"
       )

        db?.execSQL(
            "CREATE TABLE categoria("+
              "id_categoria INTEGER PRIMARY KEY,"+
              "nombre TEXT,"+
            "descripcion TEXT)"
        )
        db?.execSQL(
            "CREATE TABLE proveedor("+
                    "codigoProvee INTEGER PRIMARY KEY,"+
                    "nombre TEXT,"+
                    "nit INTEGER,"+
                    "direccion TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS productos")
        db?.execSQL("DROP TABLE IF EXISTS categoria")
        db?.execSQL("DROP TABLE IF EXISTS proveedor")
        onCreate(db)
    }


}