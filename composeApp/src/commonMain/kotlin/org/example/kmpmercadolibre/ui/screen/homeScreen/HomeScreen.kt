package org.example.kmpmercadolibre.ui.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.domain.model.Product
import org.example.kmpmercadolibre.ui.components.scaffold.ScaffoldComponent
import org.example.kmpmercadolibre.ui.components.search.SearchComponent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import kotlin.math.round

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ProductViewModel = koinViewModel()
) {
    ScaffoldComponent(navController = navController, showTopBar = false, showBottomBar = true) {

        val productState by viewModel.productsState.collectAsState()

        var query by remember { mutableStateOf("") }
        var filteredProducts by remember { mutableStateOf<List<Product>>(emptyList()) }

        LaunchedEffect(Unit) { viewModel.getAllProduct() }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            SearchComponent(
                query = query,
                onQueryChanged = { newQuery -> query = newQuery },
                onSearchClicked = {
                    if (productState is Resource.Success) {
                        val allProducts = (productState as Resource.Success<List<Product>>).data
                        filteredProducts = allProducts.filter {
                            it.title.contains(query, ignoreCase = true)
                        }
                    }
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            when (productState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
                    )
                }

                is Resource.Success -> {
                    ShowItems(products = filteredProducts.ifEmpty {
                        (productState as Resource.Success<List<Product>>).data
                    })
                }

                is Resource.Error -> {
                    Text(
                        "Error: ${(productState as Resource.Error).exception.message}",
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}


@Composable
fun ShowItems(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            val item = products[index]
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = item.image,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colors.surface),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.body1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.description ?: "No description available",
                            style = MaterialTheme.typography.body2,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "$${item.price}",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))


                        RatingStars(rating = item.rating.rate)
                    }
                }
            }
        }
    }
}

@Composable
fun RatingStars(rating: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating.toInt()) Icons.Default.Star else Icons.Outlined.Star,
                contentDescription = "Star Rating",
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "${round(rating * 10) / 10.0}", style = MaterialTheme.typography.body2)
    }
}




