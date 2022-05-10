package com.example.retrofitdemo

data class DemoDataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
){
    fun getUniqueName(id: Int,title: String):String{
        val name = title.split(" ")[0]
        return "${name}${id}"
    }
}