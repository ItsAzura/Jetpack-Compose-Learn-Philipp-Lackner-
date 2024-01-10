package com.example.thejetpackcomposelearnphilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thejetpackcomposelearnphilipplackner.ui.theme.TheJetpackComposeLearnPhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheJetpackComposeLearnPhilippLacknerTheme {
                var text by remember {
                    mutableStateOf("")
                }
                var names by remember {
                    mutableStateOf(listOf<String>("Quang"))
                }
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(6.dp)
               ) {
                   Row(
                       modifier = Modifier.fillMaxWidth()
                   ){
                       OutlinedTextField(
                           value = text,
                           onValueChange = { it ->
                               text = it
                           },
                           modifier = Modifier.weight(1f)
                       )
                       Spacer(modifier = Modifier.width(16.dp))
                       Button(
                           onClick = {
                               if (text.isNotBlank()){
                                   names = names + text
                               }
                               text = ""
                           }
                       ) {
                         Text(text = "Add")
                       }
                   }
                   LazyColumn(){
                       items(names){
                           Text(
                               text = it,
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(16.dp)
                               )
                           Divider()
                       }
                   }
               }
                
            }
        }
    }
}

