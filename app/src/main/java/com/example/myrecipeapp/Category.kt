package com.example.myrecipeapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//objects in the category
@Parcelize
//category objects are passing through different screen so it has to be parcelable.
//so it can be steralized and desrealize
//strealize does is it basically makes an object into a string
//desteralize does revert back it into objects
data class Category(val idCategory:String,
                    val strCategory:String,
                    val strCategoryThumb : String,
                    val strCategoryDescription : String):Parcelable

//creating a list named categories for the data class category
data class CategoriesResponse(val categories: List<Category>)