package com.kamau.menuapp.view

import android.content.Context

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kamau.menuapp.model.FruitsData
import com.kamau.menuapp.ui.theme.Purple80

@ExperimentalFoundationApi
@Composable
fun FruitGrid(navController: NavController){

    val context = LocalContext.current
    val dataFileString = getJsonDataFromAsset(context,"FruitsList.json")
    val gson = Gson()
    val gridSampleType = object :TypeToken<List<FruitsData>>(){}.type
    val fruitData : List<FruitsData> = gson.fromJson(dataFileString,gridSampleType)

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Purple80)
            .padding(6.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Fruits Calories and Sugar",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(10.dp)
            ) {
                items(fruitData){data->
                    FruitDataGridItem(data,navController)

                }
        }

    }


}
@Composable

fun FruitDataGridItem(data:FruitsData,navController :NavController){

    Card(modifier = Modifier
        .clickable {
            val itemVal = Gson().toJson(data)
            navController.navigate("grid_detail/$itemVal")
        }
        .padding(10.dp)
        .fillMaxSize(),
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(modifier = Modifier) {
            Image(painter = painterResource(
                id =  when(data.id){
                    1L->com.kamau.menuapp.R.drawable.apples
                    2L ->com.kamau.menuapp. R.drawable.avocado
                    3L ->com.kamau.menuapp.R.drawable.banana
                    4L -> com.kamau.menuapp.R.drawable.blackberry
                    5L -> com.kamau.menuapp.R.drawable.blueberry
                    6L ->com.kamau.menuapp. R.drawable.cantaloupe
                    7L -> com.kamau.menuapp.R.drawable.cherry
                    8L -> com.kamau.menuapp.R.drawable.grape
                    9L -> com.kamau.menuapp.R.drawable.kiwi
                    10L -> com.kamau.menuapp.R.drawable.orange
                    11L -> com.kamau.menuapp.R.drawable.peach
                    12L ->com.kamau.menuapp. R.drawable.pear
                    13L ->com.kamau.menuapp. R.drawable.pineapple
                    14L ->com.kamau.menuapp. R.drawable.plum
                    15L -> com.kamau.menuapp.R.drawable.raspberry
                    16L ->com.kamau.menuapp. R.drawable.strawberry
                    17L -> com.kamau.menuapp.R.drawable.watemelon
                    else ->com.kamau.menuapp. R.drawable.apples
                }
            ),
                contentDescription = "Grid Image",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp)),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = data.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.Black,
                fontSize =  20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.padding(1.dp))

            Text(
                text = data.desc,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(6.dp),
                color = Color.Black,
                fontSize =  16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis


                )
    }

}

fun getJsonDataFromAsset(context: Context, data: String):String {
    return context.assets.open(data).bufferedReader().use { it.readText() }
}


