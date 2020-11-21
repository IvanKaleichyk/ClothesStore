package com.koleychik.clothesstore.models.categories

abstract class Category{
    abstract fun getId() : Int
    abstract fun getResourceName() : Int
    abstract fun getImageResource() : Int
    abstract fun getSearchName() : String
}