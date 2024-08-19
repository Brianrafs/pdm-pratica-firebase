package com.example.brianpdm

import TelaPrincipal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brianpdm.ui.telas.TelaCadastro
import com.example.brianpdm.ui.telas.TelaLogin
import com.example.brianpdm.ui.theme.Navegacao1Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            Navegacao1Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Login") },
                            Modifier.background(MaterialTheme.colorScheme.secondary)
                        )
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            TelaLogin(
                                modifier = Modifier.padding(innerPadding),
                                onSigninClick = {
                                    navController.navigate("principal")
                                },
                                onRegisterClick = {
                                    navController.navigate("cadastro")
                                }
                            )
                        }
                        composable("principal") {
                            TelaPrincipal(modifier = Modifier.padding(innerPadding), onLogoffClick = {
                                navController.navigate("login")
                            })
                        }
                        composable("cadastro") {
                            TelaCadastro(
                                modifier = Modifier.padding(innerPadding),
                                onRegistrationSuccess = {
                                    navController.navigate("login")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
