package com.example.musify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musify.onboarding.domain.AppEntryUseCases
import com.example.musify.onboarding.presentation.OnboardingScreen
import com.example.musify.ui.theme.MusifyTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
object Home
@Serializable
object OnBoarding

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    startDestination?.let { destination ->
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
            Greeting("Android")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusifyTheme {
    }
}