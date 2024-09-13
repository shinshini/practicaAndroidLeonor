package com.example.myapplication.clase

class Producto(nombre:String, precio:Double, productos:Int, proveedor: Int, categoria: Int) {

    private var nombre:String = nombre
    private var precio:Double = precio
    private var  id_productos:Int = productos
    private var  id_provee:Int= proveedor
    private var  id_cate:Int= categoria

    //get
    fun getNombre():String{
        return  nombre
    }
    fun getPrecio():Double{
        return  precio
    }
    fun getProductos():Int{
        return id_productos
    }
    fun getProveedor():Int{
        return id_provee
    }
    fun getCategoria():Int{
        return id_cate
    }
    //set
    fun setNombre(nombre: String){
        this.nombre= nombre
    }
    fun setPrecio(precio: Double){
        this.precio = precio
    }
    fun setProductos(productos: Int){
        this.id_productos=productos
    }
    fun setProveedor(proveedor:Int){
        this.id_provee=proveedor
    }
    fun setCategoria(categoria:Int){
        this.id_cate=categoria
    }
    fun calIVA(iva:Double): Double{
        val total:Double =precio * iva
        return total
    }
}