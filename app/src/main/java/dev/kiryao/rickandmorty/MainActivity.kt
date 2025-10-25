package dev.kiryao.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import dev.kiryao.rickandmorty.home.presentation.HomeAppBar
import dev.kiryao.rickandmorty.home.presentation.HomeScreen
import dev.kiryao.rickandmorty.home.presentation.HomeViewModel
import dev.kiryao.rickandmorty.ui.theme.RickAndMortyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                Scaffold(
                    topBar = { HomeAppBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val viewModel = hiltViewModel<HomeViewModel>()
                    val characters = viewModel.characterPagerFlow.collectAsLazyPagingItems()
                    HomeScreen(
                        characters = characters,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}