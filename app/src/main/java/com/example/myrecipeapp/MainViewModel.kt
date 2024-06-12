package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainViewModel : ViewModel(){
    //contains the data class recipe state objects.
    private var _categorieState = mutableStateOf(RecipeState())
    //categoriesState is of state<recipestate>(datatype/object) type contains value _category
    val categoriesState : State<RecipeState> = _categorieState

    //initializing the fetchCategories function
    //when the viewmodel is loaded this init function calls the fetchcategories function
    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        //provides a launch for suspend functions to be processed.
        //coroutine which allows us to call functons that are suspend function
        viewModelScope.launch {

            //coroutine allows to run routine in background.
            try {
                //calls the recipeService which is under api file. It contains the retrofit base Url with the interface APIService.
                val response= recipeService.getCategories()
                //calling Recipestate data class obejects values to change.
                _categorieState.value = _categorieState.value.copy(
                    //listing te categories
                    list=response.categories,
                    loading = false,
                    error = null
                )
            //if any error occurs in calling retrofit baseUrl or Api service it calls the catch function
            }catch (e:Exception){
                //taking the error part and changing recipe state to show error message.
                _categorieState.value=_categorieState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}")
            }

        }
    }

    //data class contains objects used for fetching categories.
    data class RecipeState(val loading: Boolean = true,
                           val list: List<Category> = emptyList(),
                           val error: String? = null)


}