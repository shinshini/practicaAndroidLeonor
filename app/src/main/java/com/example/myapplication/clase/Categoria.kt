package com.example.myapplication.clase

class Categoria constructor(idcategoria:Int,nombre:String,descripcion:String) {
    private var nombre:String = nombre
    private var idcategoria:Int = idcategoria
    private var descripcion:String = descripcion
    //get
    fun getNombre():String{
        return  nombre
    }
    fun getCategoria():Int{
        return idcategoria
    }
    fun getDescripcion():String{
        return  descripcion
    }
    //set
    fun setNombre(nombre: String){
        this.nombre= nombre
    }
    fun setCategoria(idcategoria:Int){
        this.idcategoria=idcategoria
    }
    fun setDescripcion(descripcion:String){
        this.descripcion= descripcion
    }
}