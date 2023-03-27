package com.example.application

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Xml
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.application.Api.ApiCli
import com.example.application.types.Eleve
import com.example.application.ui.theme.ApplicationTheme
import org.xmlpull.v1.XmlPullParser
import java.util.Objects


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
                    ModalDrawer(drawerContent = {Menu()}, drawerBackgroundColor = Color.Black, drawerElevation = 20.dp,) {
                        rememberDrawerState(initialValue = DrawerValue.Closed)
                        MainCompose(modifier = Modifier)
                        var state = remember {
                            mutableStateOf(0)
                        }
                        }
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
        Text(text = "Menu : ")
        TextButton(onClick = {  }) {
            Text(text = "Liste des formations")
        }
        TextButton(onClick = {  }) {
            Text(text = "Liste des élèves")

        }
        TextButton(onClick = {  }) {
            Text(text = "Liste des séances à venir")
        }
        TextButton(onClick = { }) {
            Text(text = "Evaluations")
        }
    }
    }
}
//greeting avec placeholder
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainCompose(modifier: Modifier) {
    var api: ApiCli? = MyApplication.appContext?.let { ApiCli(context = it) }
    var etat = remember {
        mutableStateOf(0)
    }
    var wait = remember {
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
        Row() {
            LazyColumn {
                item {Text(text = "Liste des élèves :")}
                if (etat.value == 1){
                    for (elv: Eleve in RecupEleves()!!) {
                        item {
                            StudentRow(student = elv)
                        }
                    }
                }
            }
        }
        Row(modifier) {
            when (etat.value) {
                2 -> Text(
                    text = "Liste des formations :",
                    modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
                3 -> Text(text = "Profil :", modifier.fillMaxSize(), textAlign = TextAlign.Center)
            }
        }
    }
}

fun RecupEleves() : ArrayList<Eleve>?{
    var ac = MyApplication.appContext?.let { ApiCli(it) }

    var lstEleves : ArrayList<Eleve>? = ac?.RecupererEleves()

   return lstEleves

}

@Composable
fun StudentRow(student: Eleve) {
    Card(modifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(
                student.nom,
                fontSize = 25.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(10.dp)
            )
            Text(student.id.toString(), color = Color.Gray, modifier = Modifier.padding(10.dp))
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