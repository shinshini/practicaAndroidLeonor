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
import com.example.myapplication.clase.Proveedo
import com.example.myapplication.db.AdminSQLiteOpenHelper

class Proveedor : AppCompatActivity() {
    lateinit var  btnRegistraProvee:Button
    lateinit var  txtnom:EditText
    lateinit var  txtnit:EditText
    lateinit var  txtcodigoProvee:EditText
    lateinit var  txtDireccion:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_proveedor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }
    fun cargarR(){
        btnRegistraProvee=findViewById(R.id.btnRegistroProveedor)
        txtnit=findViewById(R.id.txtNIT)
        txtnom=findViewById(R.id.txtNombreProveedor)
        txtcodigoProvee=findViewById(R.id.txtCodigoProveedor)
        txtDireccion=findViewById(R.id.txtDireccionProve)

    }
    fun estadoBoton(){
        btnRegistraProvee.setOnClickListener{
            val adminsql=AdminSQLiteOpenHelper(this,"administracion",null,1)
            val db = adminsql.writableDatabase
            val registro=ContentValues()

            val objeto = Proveedo(txtcodigoProvee.text.toString(),txtnom.text.toString(), txtDireccion.text.toString(),txtnit.text.toString().toInt())

            registro.put("id_proveedor",objeto.getCodProvee())
            registro.put("nombre",objeto.getNombre())
            registro.put("nit",objeto.getNit())
            registro.put("direccion",objeto.getDireccion())
            db.insert("proveedor",null,registro)
            db.close()
            txtcodigoProvee.setText("")
            txtnom.setText("")
            txtnit.setText("")
            txtDireccion.setText("")
            Toast.makeText(this,"Se cargaron los datos del proveedor",Toast.LENGTH_SHORT).show()
        }
    }
}