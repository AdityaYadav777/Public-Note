package com.aditya.notev1.Model

import java.text.SimpleDateFormat

import java.util.Date


class Data{




    val currentD = SimpleDateFormat("dd-MM-yyyy \t HH:mm:ss").format(Date())
    var data: String? =null
      var  key: String? =null

        var name:String?=null

    constructor()

     constructor( data:String,key:String){
         this.data=data
         this.key=key



     }




}
