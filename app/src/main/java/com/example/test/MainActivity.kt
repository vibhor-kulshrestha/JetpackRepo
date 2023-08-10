package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val cm = CategoryViewModel()
        super.onCreate(savedInstanceState)
        Log.v("myLog","onCreate")
        setContent {
            MaterialTheme {
                CategoryScreen()
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    private fun CategoryScreen() {
        Log.v("myLog","CategoryScreen-->start")
        val viewModel: CategoryViewModel = viewModel()
        viewModel.getCategories()
        Log.v("myLog","CategoryScreen-->afterApicall")
        val categories: List<Category> =viewModel.categoryList
        Log.v("myLog", "CategoryScreen-->categories-->$categories")
        LazyColumn(
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)
        ) {
            items(categories) { category ->
                Log.v("myLog","CategoryItem-->start-->${category.subCategory}")
                CategoryItem(category)
                Log.v("myLog","CategoryItem-->start-->${category.subCategory}")
            }
        }

    }

    @Composable
    fun CategoryItem(category: Category) {
        Log.v("myLog","CategoryItem-->start-->$category")
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(text = category.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            category.subCategory.forEach{subCategory ->
                SubCategoryItem(subCategory)
                Log.v("myLog","CategoryItem-->end-->$subCategory")
            }
        }
    }

    @Composable
    fun SubCategoryItem(subCategory: SubCategory) {
        Log.v("myLog","subCategory-->start-->$subCategory")
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(subCategory.icon),
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = subCategory.name)
        }
    }

        private fun viewModel(): CategoryViewModel {
            return CategoryViewModel()
        }
    }
