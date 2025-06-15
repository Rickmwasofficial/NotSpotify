package com.example.musify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musify.app.albums.presentation.AlbumsUiScreen
import com.example.musify.app.core.components.IconButtonText
import com.example.musify.app.favorites.presentation.FavoritesUiScreen
import com.example.musify.app.home.presentation.HomeUiScreen
import com.example.musify.app.library.presentation.LibraryUiScreen
import com.example.musify.app.onboarding.domain.AppEntryUseCases
import com.example.musify.app.onboarding.presentation.OnboardingScreen
import com.example.musify.ui.theme.MusifyTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.serialization.Serializable

@Serializable
object Home
@Serializable
object OnBoarding
@Serializable
object Library
@Serializable
object Albums
@Serializable
object Favorites

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Keep splash screen visible until data is loaded
        var keepSplashScreen = true
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        actionBar?.hide()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MusifyTheme {
                var startDestination by remember { mutableStateOf<Any?>(null) }

                LaunchedEffect(Unit) {
                    appEntryUseCases.readAppEntry().collect { isAppEntryComplete ->
                        startDestination = if (isAppEntryComplete) {
                            Home
                        } else {
                            OnBoarding
                        }
                        // Dismiss splash screen once destination is determined
                        keepSplashScreen = false
                    }
                }

                // Only render UI when startDestination is determined
                startDestination?.let { destination ->
                    // Get current route to determine if bottom bar should show
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    // Define routes where bottom bar should be hidden
                    val hideBottomBarRoutes = setOf(
                        OnBoarding::class.qualifiedName
                    )

                    val shouldShowBottomBar = currentRoute !in hideBottomBarRoutes

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (shouldShowBottomBar) {
                                BottomBar(navController = navController)
                            }
                        }) { innerPadding ->
                        AppNavHost(navController, destination, Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination: Any, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<OnBoarding> {
            OnboardingScreen()
        }
        composable<Home> {
            HomeUiScreen()
        }
        composable<Library> {
            LibraryUiScreen()
        }
        composable<Favorites> {
            FavoritesUiScreen()
        }
        composable<Albums> {
            AlbumsUiScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar(
        modifier = modifier.height(110.dp),
        contentPadding = PaddingValues(vertical = 5.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButtonText(R.drawable.home, R.string.home, currentRoute, { navController.navigate(Home) }, Modifier.weight(1f))
            IconButtonText(R.drawable.music_note, R.string.libraryText, currentRoute, { navController.navigate(Library) }, Modifier.weight(1f))
            IconButtonText(R.drawable.album, R.string.albums, currentRoute, { navController.navigate(Albums) }, Modifier.weight(1f))
            IconButtonText(R.drawable.favorite_24dp_e3e3e3, R.string.favorites, currentRoute, { navController.navigate(Favorites) }, Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusifyTheme {
        AppNavHost(rememberNavController(), Home)
    }
}