package com.example.test

data class Category(val id: Int,val name:String,val parent_id:Int,val icon:String,val subCategory: List<SubCategory>)
