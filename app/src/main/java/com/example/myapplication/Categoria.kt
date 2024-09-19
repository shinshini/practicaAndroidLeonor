package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.clase.Categorias
import com.example.myapplication.db.AdminSQLiteOpenHelper

class Categoria : AppCompatActivity() {
    lateinit var  btnRegistrarCa: Button
    lateinit var  txtnom:EditText
    lateinit var  txtdescripcion:EditText
    lateinit var  txtcodCategoria:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categoria)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }
    fun cargarR(){
        btnRegistrarCa=findViewById(R.id.btncategoria)
        txtnom=findViewById(R.id.txtNombrecate)
        txtdescripcion=findViewById(R.id.txtDescripcion)
        txtcodCategoria=findViewById(R.id.txtCodigo)
    }
    fun estadoBoton(){
        btnRegistrarCa.setOnClickListener{
            val adminsql=AdminSQLiteOpenHelper(this,"administracion",null,1)
            val db=adminsql.writableDatabase
            val registro=ContentValues()
            val objeto= Categorias (txtcodCategoria.text.toString().toInt(),txtnom.text.toString(),txtdescripcion.text.toString())

            registro.put("codigoCate",objeto.getCategoria())
            registro.put("nombre",objeto.getNombre())
            registro.put("descripcion",objeto.getDescripcion())
            db.insert("categoria",null,registro)
            db.close()
            txtcodCategoria.setText("")
            txtnom.setText("")
            txtdescripcion.setText("")
            Toast.makeText(this,"se cargaron los datos de categoria",Toast.LENGTH_SHORT).show()
        }
    }
}