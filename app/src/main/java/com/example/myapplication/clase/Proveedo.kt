package com.example.myapplication.clase

class Proveedo constructor (codProve:String, nombre:String, direccion:String, nit:Int){

    private var codProve:String = codProve
    private var nombre:String = nombre
    private var direccion:String = direccion
    private var nit:Int = nit


    fun getCodProvee():String{
        return  codProve
    }
    fun getNombre():String{
        return  nombre
    }
    fun getDireccion():String{
        return  direccion
    }
    fun getNit():Int{
        return  nit
    }

   //set
   fun setCodProvee(codProve: String){
       this.codProve= codProve
   }
    fun setNombre(nombre: String){
        this.nombre= nombre
    }
    fun setDireccion(direccion:String){
        this.direccion= direccion
    }
    fun setNit(ni:Int){
        this.nit= nit
    }

}
