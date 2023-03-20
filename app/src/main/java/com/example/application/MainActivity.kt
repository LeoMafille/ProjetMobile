package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ModalDrawer(drawerContent = {Menu()}) {
                        rememberDrawerState(initialValue = DrawerValue.Closed)
                    }
                        Greeting(modifier = Modifier)
                    }
                }
            }
        }
    }

//menu déroulant
@Composable
fun Menu(){
    Column() {
        Text(text = "Menu : ")
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Liste des formations")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Liste des élèves")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Liste des scéances à venir")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Evaluations")
        }
    }
}
//greeting avec placholder
@Composable
fun Greeting(modifier: Modifier) {
    Text(text = "Hola chicos :)")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationTheme {
        Greeting(Modifier)
    }
}