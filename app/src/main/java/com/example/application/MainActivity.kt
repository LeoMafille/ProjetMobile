package com.example.application

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.Api.ApiCli
import com.example.application.types.Eleve
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
                    //ajout du menu déroulant a l'activité
                    ModalDrawer(drawerContent = {Menu()}) {
                        rememberDrawerState(initialValue = DrawerValue.Closed)
                    }
                        MainCompose(modifier = Modifier)
                    }
                }
            }
        }
    }

//menu déroulant
@Composable
fun Menu(){
    Row() {
    Column() {
        Text(text = "")
        Text(text = "")
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
}
//greeting avec placeholder
@Composable
fun MainCompose(modifier: Modifier) {
    var api:ApiCli= ApiCli(context = MyApplication.appContext)
    var etat = remember {
        mutableStateOf(0)
    }
    Column() {
        Row() {
            OutlinedButton(onClick = { etat.value = 1 }, Modifier.background(if (etat.value==1) Color.Red else Color.Black))  {
                Text(text = "Liste des élèves")
            }
            OutlinedButton(onClick = { etat.value = 2 }, modifier = Modifier.background(if (etat.value==2) Color.Red else Color.Black) ) {
                Text(text = "Liste des formations")
            }
            IconButton(onClick = { etat.value = 3}, Modifier.background(if (etat.value==3) Color.Red else Color.Black)) {
                Icon(painter = painterResource(id = R.drawable.profil), contentDescription = "icone profil")
            }
        }
//menu dessus appli
        Row(modifier) {
            when (etat.value) {
                1 -> {Text(text = "Liste des élèves :", modifier.fillMaxSize(), textAlign = TextAlign.Center)
                for(eleve:Eleve in api.AfficheEleves()){
                    Text(text = "eleve")
                }}
                2 -> Text(text = "Liste des formations :", modifier.fillMaxSize(), textAlign = TextAlign.Center)
                3 -> Text(text = "Profil :", modifier.fillMaxSize(), textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApplicationTheme {
        MainCompose(Modifier)
    }
}