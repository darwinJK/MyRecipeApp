package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//build connection to base url and do it in a format so that we will get well,
// we can concert whatever we get into objects in kotlin.
private val retrofit=Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

//we wants to have the service that allow us to get this categories.php file
// which then we will convert into json object that we can then use.
val recipeService= retrofit.create(ApiService::class.java)


interface ApiService{
    //get keyword is usd to specify the type of request that should be made to particular URL(categories.php)
    //used to generate the necessary code to make the network request send a get request to  URL and process the response data.
    @GET("categories.php")
    //part of coroutine api  that provides a straightforward and structured way to manage concurrency,
    //Basically running a multiple tasks at the same time, like the UI takes care of well, UI thread.
    //coroutines are powerful and lightweight concurrency framework in kotlin, specifically designed for handling asynchronous and non blocking operations.
    //also do reading and writing to database or handling time consuming operations without blocking the main thread(UI thread)
    suspend fun getCategories():CategoriesResponse

}