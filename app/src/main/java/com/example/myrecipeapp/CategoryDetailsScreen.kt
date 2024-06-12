package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryDetailScreen(category: Category){
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = category.strCategory, textAlign = TextAlign.Center)
        Image(
            painter = rememberAsyncImagePainter( category.strCategoryThumb),
            contentDescription = "${category.strCategory} Thumbnail", // handle accessibility
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(1f)
        )
        //Stretch lines of text that end with a soft line break to fill the width of the container.
        //Lines that end with hard line breaks are aligned towards the Start edge.
        Text(text = category.strCategoryDescription,
            textAlign = TextAlign.Justify,
            //used to scroll the content discription without affecting the name.
            modifier = Modifier.verticalScroll(rememberScrollState()))
    }
}