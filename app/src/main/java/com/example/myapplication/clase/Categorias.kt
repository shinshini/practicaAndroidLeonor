package com.example.myapplication.clase

class Categorias constructor(idcategoria:String, nombre:String, descripcion:String) {
    private var nombre:String = nombre
    private var idcategoria:String = idcategoria
    private var descripcion:String = descripcion
    //get
    fun getNombre():String{
        return  nombre
    }
    fun getCategoria():String{
        return idcategoria
    }
    fun getDescripcion():String{
        return  descripcion
    }
    //set
    fun setNombre(nombre: String){
        this.nombre= nombre
    }
    fun setCategoria(idcategoria:String){
        this.idcategoria=idcategoria
    }
    fun setDescripcion(descripcion:String){
        this.descripcion= descripcion
    }
}