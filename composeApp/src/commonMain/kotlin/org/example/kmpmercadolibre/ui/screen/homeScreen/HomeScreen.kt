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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import org.example.kmpmercadolibre.ui.components.scaffold.ScaffoldComponent
import org.example.kmpmercadolibre.ui.components.search.SearchComponent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    ScaffoldComponent(navController = navController, showTopBar = true, showBottomBar = true) {

        // Obtenemos el estado de búsqueda y el texto de consulta desde el ViewModel
        val searchState by viewModel.searchState.collectAsState()
        var query by remember { mutableStateOf("") }

        // Diseño principal
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Componente de búsqueda en la parte superior
            SearchComponent(
                query = query,
                onQueryChanged = { newQuery ->
                    query = newQuery
                },
                onSearchClicked = {
                    viewModel.search(query) // Acción de búsqueda
                }
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre el campo de búsqueda y el resto del contenido

            // Resultados o estado de carga
            when (searchState) {
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp)
                    )
                }
                is Resource.Success -> {
                    val result = (searchState as Resource.Success).data

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(result.results ?: emptyList()) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                elevation = 4.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Imagen del producto
                                    AsyncImage(
                                        model = item.thumbnail, // Cambia 'imageUrl' por el nombre correcto del atributo
                                        contentDescription = "Product Image",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(MaterialTheme.colors.surface),
                                        contentScale = ContentScale.Crop
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    // Información del producto
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
                                            text = item.thumbnail ?: "No description available",
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
                                    }
                                }
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    Text(
                        "Error: ${(searchState as Resource.Error).exception.message}",
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}