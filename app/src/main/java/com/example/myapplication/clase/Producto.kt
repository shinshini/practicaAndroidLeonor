package com.example.myapplication.clase

class Producto(nombre:String, precio:Double, productos:Int, proveedor: String, categoria: String) {

    private var nombre:String = nombre
    private var precio:Double = precio
    private var  id_productos:Int = productos
    private var  id_provee:String= proveedor
    private var  id_cate:String= categoria

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
    fun getProveedor():String{
        return id_provee
    }
    fun getCategoria():String{
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
    fun setProveedor(proveedor:String){
        this.id_provee=proveedor
    }
    fun setCategoria(categoria:String){
        this.id_cate=categoria
    }
    fun calIVA(iva:Double): Double{
        val total:Double =precio * iva
        return total
    }
}