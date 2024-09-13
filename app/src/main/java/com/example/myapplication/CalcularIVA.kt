package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.clase.Producto
import com.example.myapplication.db.AdminSQLiteOpenHelper

class CalcularIVA : AppCompatActivity() {
    lateinit var  btnCalcular: Button
    lateinit var  txtPrecio:EditText
    lateinit var  txtNombre: EditText
    lateinit var  tvResul: TextView
    lateinit var spLista: Spinner
    lateinit var lisPro:ListView
    lateinit var  btnbuscarProducto: Button
    lateinit var  productosList: MutableList<String>
    lateinit var  adapterListView:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calcular_iva)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
        cargarListaProducto()

        val listaPaises= arrayOf("USA","BOL","ESP")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_gallery_item,listaPaises)
        spLista.adapter=adaptador1
    }
    private fun cargarR(){
        btnCalcular=findViewById(R.id.btnCalcularIVA)
        txtPrecio=findViewById(R.id.txtPrecioca)
        tvResul=findViewById(R.id.tvResultado)
        spLista=findViewById(R.id.spPaises)
        lisPro=findViewById(R.id.listaProductos)
        txtNombre=findViewById(R.id.txtNombreProducto)
        btnbuscarProducto=findViewById(R.id.btnbuscarproducto)
    }
    fun estadoBoton(){
        btnCalcular.setOnClickListener(){
            val laptop = Producto(
                txtNombre.text.toString(),
                txtPrecio.text.toString().toDouble(),
                1,
                0,
                0
            )

            when(spLista.selectedItem.toString()){
                "USA"->productosList.add(laptop.getNombre()+", "+laptop.getPrecio()+"  IVA: "+laptop.calIVA(0.03).toString())
                "BOL"-> productosList.add(laptop.getNombre()+ ", " +laptop.getPrecio()+ "IVA: "+laptop.calIVA(0.13).toString())
                "ESP"-> productosList.add(laptop.getNombre()+ ", " +laptop.getPrecio()+ "IVA: "+laptop.calIVA(0.05).toString())
            }
            lisPro.adapter=adapterListView
        }
        btnbuscarProducto.setOnClickListener(){
            val admin=AdminSQLiteOpenHelper(this, "administracion",null,1)
            val bd= admin.writableDatabase

            val fila = bd.rawQuery("select nombre, precio from productos where id_productos=${txtNombre.text.toString()}",null)
            if(fila.moveToFirst()){
                txtNombre.setText(fila.getString(0))
                txtPrecio.setText(fila.getString(1))
            }else
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show()
                bd.close()

        }
    }
    fun cargarListaProducto(){
        productosList= mutableListOf("3500")
        adapterListView=ArrayAdapter(this, android.R.layout.simple_list_item_1,productosList)
        lisPro.adapter=adapterListView
    }
}