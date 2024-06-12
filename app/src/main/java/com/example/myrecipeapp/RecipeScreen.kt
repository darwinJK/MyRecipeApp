package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter



@Composable
fun RecipeScreen(modifier : Modifier = Modifier,
                 viewstate:MainViewModel.RecipeState,
                 navigateToDetail:(Category) -> Unit){


//box for the userInterface
    Box(modifier=Modifier.fillMaxSize()){
        //if programs loads
        when{
            viewstate.loading ->{
                //loading symbol if the program is loading
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            //else if progroms get error display
            viewstate.error!=null ->{
                Text("ERROR OCCURRED")
            }
            else -> {
                //Display categoryScreen() function
                CategoryScreen(categories =viewstate.list,navigateToDetail)

            }
        }
    }
}
@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail:(Category) -> Unit){
    //cells used in a row..unlimited access of list..
    LazyVerticalGrid(GridCells.Fixed(2),modifier=Modifier.fillMaxSize()){
        //items is a list of content used to show at the user interface and it calls 'categories' in the category file
        items(categories){
                category ->
            //calling function categoryItem with the objects of category.
            categoryItem(category=category,navigateToDetail)
        }
    }
}



@Composable
fun categoryItem(category: Category,
                 navigateToDetail:(Category) -> Unit){


    Column(modifier= Modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally) {
        //image is adding to the user interface.
        Image(
            //the image is is called frm the internet using rememberAsyncImagePainter calls from the category
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)

        )
        //text under the image calls from the category

        Text(text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier=Modifier.padding(top=4.dp))

    }
}