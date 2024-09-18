package com.example.myapplication

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
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
    lateinit var  txtnombrecate: EditText
    lateinit var  txtddescripcion: EditText
    lateinit var  btnBuscarProve:Button
    lateinit var  txtnombreprovee: EditText
    lateinit var  txtnit: EditText
    lateinit var  txtdireccionprovee: EditText
    lateinit var listProductos: ListView
    lateinit var productosList:MutableList<String>
    lateinit var adapterListView:ArrayAdapter<String>

    var codigoProvee:String= null.toString()

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
        cargarListaProducto()
    }
    fun cargarR(){
        btnRegistrarPro=findViewById(R.id.btnRegistroProducto)
        txtnom=findViewById(R.id.txtNombreProd)
        txtPrecio=findViewById(R.id.txtPrecio)
        txtcodigo=findViewById(R.id.txtCodigoProducto)
        btnBuscarCate=findViewById(R.id.btnbuscarCategoria)
        txtnombrecate=findViewById(R.id.txtNombrecate)
        txtddescripcion=findViewById(R.id.txtDescripcion)
        btnBuscarProve=findViewById(R.id.btnbuscarProveedor)
        txtnombreprovee=findViewById(R.id.txtNombreProvee)
        txtnit=findViewById(R.id.txtNIT)
        txtdireccionprovee=findViewById(R.id.txtDireccionProve)
        listProductos=findViewById(R.id.listaProductos)
    }
    fun estadoBoton(){
        btnRegistrarPro.setOnClickListener {
            // Verificamos que los campos no estén vacíos
            if (txtcodigo.text.isEmpty() || txtnom.text.isEmpty() || txtPrecio.text.isEmpty() || txtnombrecate.text.isEmpty() || txtnombreprovee.text.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val adminsql = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val db = adminsql.writableDatabase
                val registro = ContentValues()


                // Creamos el objeto Producto
                val objeto = Producto(txtnom.text.toString(),txtPrecio.text.toString().toDouble(),txtcodigo.text.toString().toInt(),codigoProvee.toString().toInt(), txtnombreprovee.text.toString().toInt())


                // Insertamos en la base de datos
                registro.put("id_productos", objeto.getProductos())
                registro.put("nombre", objeto.getNombre())
                registro.put("precio", objeto.getPrecio())
                registro.put("id_categoria", objeto.getCategoria())
                registro.put("id_proveedor", objeto.getProveedor())

                db.insert("productos", null, registro)
                db.close()

                // Limpiamos los campos de texto
                txtcodigo.setText("")
                txtnom.setText("")
                txtPrecio.setText("")
                txtnombrecate.setText(" ")
                txtnombrecate.setText("")
                txtddescripcion.setText("")
                txtnombreprovee.setText("")
                txtnombreprovee.setText("")
                txtnit.setText("")
                txtdireccionprovee.setText("")

                Toast.makeText(this, "Se cargaron los datos del producto", Toast.LENGTH_SHORT).show()

                // Añadimos el nuevo producto a la lista y notificamos al adaptador
                productosList.add(
                    "ID: ${objeto.getProductos()}  |   Nombre: ${objeto.getNombre()} |  Precio: ${objeto.getPrecio()}  |  id_Categoría: ${objeto.getCategoria().toInt()}  |  id_Proveedor: ${objeto.getProveedor().toInt()}"
                )

                // Notificamos al adaptador que los datos han cambiado
                adapterListView.notifyDataSetChanged()
                txtnombrecate.setText("")
                txtnombreprovee.setText("")

                Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Manejamos cualquier excepción que ocurra durante la ejecución
                Toast.makeText(this, "Error al registrar el producto: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }

        }
        btnBuscarCate.setOnClickListener(){
            val admin=AdminSQLiteOpenHelper(this,"administracion",null,1)
            val bd=admin.writableDatabase

            val fila=bd.rawQuery("select nombre,descripcion from categoria where id_categoria=${txtnombrecate.text.toString().toInt()}",null)
            if (fila.moveToFirst()){
                txtnombrecate.setText(fila.getString(0))
                txtddescripcion.setText(fila.getString(1))


            }else
                Toast.makeText(this,"No existe un dicho codigo de categoria",Toast.LENGTH_SHORT).show()
                bd.close()

        }
        btnBuscarProve.setOnClickListener(){
            val admin=AdminSQLiteOpenHelper(this,"administracion",null,1)
            val bd=admin.writableDatabase

            val fila=bd.rawQuery("select  codigoProvee,nombre,nit,direccion from proveedor where id_proveedor=${txtnombreprovee.text.toString().toInt()}",null)
            if(fila.moveToFirst()){
                codigoProvee = fila.getString(0)

                txtnombreprovee.setText(fila.getString(1))
                txtnit.setText(fila.getString(2))
                txtdireccionprovee.setText(fila.getString(3))

            }else{
                Toast.makeText(this, "No existe un dicho codigo de proveedor", Toast.LENGTH_SHORT).show()
            }
            bd.close()
        }


    }


    fun cargarListaProducto(){
        productosList = mutableListOf()
        adapterListView = ArrayAdapter(this, android.R.layout.simple_list_item_1,productosList)
        listProductos.adapter=adapterListView
    }
}