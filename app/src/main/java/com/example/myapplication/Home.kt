package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.clase.Proveedo

class Home : AppCompatActivity() {
    lateinit var btnLimpieza: CardView
    lateinit var btnProveedor: CardView
    lateinit var btnCategoria: CardView
    lateinit var  btnCalcularIVA: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    cargarR()

    estadoBoton()
}

fun cargarR(){
    btnLimpieza = findViewById(R.id.btnProducto)
    btnProveedor = findViewById(R.id.btnProveerdor)
    btnCategoria=findViewById(R.id.btnCategoria)
    btnCalcularIVA=findViewById(R.id.btnCalcularIVA)
}

fun estadoBoton(){
    btnLimpieza.setOnClickListener{
        val i = Intent(this, Productos::class.java)
        startActivity(i)
    }

    btnProveedor.setOnClickListener{
        val i = Intent(this, Proveedor::class.java)
        startActivity(i)
    }
    btnCategoria.setOnClickListener{
        val i = Intent(this, Categoria::class.java)
        startActivity(i)
    }
    btnCalcularIVA.setOnClickListener{
        val i = Intent(this, CalcularIVA::class.java)
        startActivity(i)
    }
}
}