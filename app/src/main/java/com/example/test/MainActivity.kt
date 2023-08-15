package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.intuit.sdp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val cm = CategoryViewModel()
        super.onCreate(savedInstanceState)
        Log.v("myLog", "onCreate")
        setContent {
            MaterialTheme {
                val viewModel: CategoryViewModel = viewModel()
                CategoryScreen(viewModel)
            }
        }
    }

    @Composable
    private fun CategoryScreen(viewModel: CategoryViewModel) {
        Log.v("myLog", "CategoryScreen-->start")

        Log.v("myLog","isLoading2-->" + viewModel.isLoading.value)
//        val isLoading = remember {
//            mutableStateOf(viewModel.isLoading.value)
//        }
        LaunchedEffect(key1 = Unit){
            viewModel.getCategories()
        }

        val categories: List<Category> = viewModel.categoryList
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(getSdp(R.dimen._16sdp))
        ) {
            items(categories) { category ->
                CategoryItem(category)
            }
        }
        if(viewModel.isLoading.value){
            LoadingIndicator()
        }
    }
    @Composable
    fun LoadingIndicator(){
        Box(modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
            ){
            CircularProgressIndicator()
        }
    }
    @Composable
    fun getSdp(value :Int) : Dp{
        return dimensionResource(id = value)
    }
    @Composable
    fun CategoryItem(category: Category) {
        val subCatVisible = remember {
            mutableStateOf(true)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = category.name, modifier = Modifier.clickable { subCatVisible.value = !subCatVisible.value }, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(getSdp(R.dimen._8sdp)))
            if(subCatVisible.value) {
                category.subCategory.forEach { subCategory ->
                    SubCategoryItem(subCategory)
                }
            }
        }
    }

    @Composable
    fun SubCategoryItem(subCategory: SubCategory) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(subCategory.icon),
                contentDescription = null,
                modifier = Modifier.size(getSdp(R.dimen._32sdp)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(getSdp(R.dimen._8sdp)))
            Text(text = subCategory.name)
        }
    }

    private fun viewModel(): CategoryViewModel {
        return CategoryViewModel()
    }
}
