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
import com.example.myapplication.clase.Producto
import com.example.myapplication.db.AdminSQLiteOpenHelper

class Productos : AppCompatActivity() {
    lateinit var  btnRegistrarPro:Button
    lateinit var  txtnom : EditText
    lateinit var  txtPrecio: EditText
    lateinit var txtcodigo:EditText
    lateinit var  btnBuscarCate:Button
    lateinit var  btnBuscarProveedor:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }
    fun cargarR(){
        btnRegistrarPro=findViewById(R.id.btnRegistroProducto)
        txtnom=findViewById(R.id.txtNombreProd)
        txtPrecio=findViewById(R.id.txtPrecio)
        txtcodigo=findViewById(R.id.txtCodigoProducto)
        btnBuscarCate=findViewById(R.id.btnbuscarCategoria)
        btnBuscarProveedor=findViewById(R.id.btnRegistroProveedor)
    }
    fun estadoBoton(){
        btnRegistrarPro.setOnClickListener{
       val adminsql=AdminSQLiteOpenHelper(this, "administracion",null,1)
        val db= adminsql.writableDatabase
        val registro = ContentValues()

        val objeto= Producto(txtnom.text.toString(),txtPrecio.text.toString().toDouble(),txtcodigo.text.toString().toInt())
            registro.put("id_productos",objeto.getProductos())
            registro.put("nombre",objeto.getNombre())
            registro.put("precio",objeto.getPrecio())
            db.insert("productos",null,registro)
            db.close()
            txtcodigo.setText("")
            txtnom.setText("")
            txtPrecio.setText("")
            Toast.makeText(this,"se cargaron los datos del producto",Toast.LENGTH_SHORT).show()
        }
    }
}