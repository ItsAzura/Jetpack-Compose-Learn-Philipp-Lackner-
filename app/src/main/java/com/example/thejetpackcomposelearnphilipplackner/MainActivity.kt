package com.example.thejetpackcomposelearnphilipplackner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.thejetpackcomposelearnphilipplackner.ui.theme.TheJetpackComposeLearnPhilippLacknerTheme
import androidx.compose.material3.AlertDialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheJetpackComposeLearnPhilippLacknerTheme {
                var text by remember { mutableStateOf("") }
                var names by remember { mutableStateOf(listOf("Quang","Azura")) }
                var selectedIndexes by remember { mutableStateOf(setOf<Int>()) }
                var showDeleDialog by remember { mutableStateOf(false) }
                var showEditDialog by remember { mutableStateOf(false) }
                var itemToDeleteIndex by remember { mutableStateOf(-1) }
                var itemToEditIndex by remember { mutableStateOf(-1) }
                var editItemText by remember { mutableStateOf("") }
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
                       itemsIndexed(names){index , item ->
                           Row(
                               modifier = Modifier.fillMaxWidth()
                           ){
                               Text(
                                   text = item,
                                   modifier = Modifier
                                       .fillMaxWidth()
                                       .padding(16.dp)
                                       .weight(1f)
                               )
                               Checkbox(
                                   checked = index in selectedIndexes,
                                   onCheckedChange = {
                                       selectedIndexes = if(it){
                                           selectedIndexes + index
                                       }
                                       else
                                       {
                                           selectedIndexes - index
                                       }
                                   }
                               )
                               Icon(
                                   imageVector = Icons.Default.Edit,
                                   contentDescription ="Edit",
                                   modifier = Modifier
                                       .padding(
                                           top = 10.dp,
                                           start = 8.dp,
                                           end = 8.dp,
                                       )
                                       .size(24.dp)
                                       .clickable {
                                           showEditDialog = true
                                           itemToEditIndex = index
                                           editItemText = item
                                       }
                               )
                               Icon(
                                   imageVector = Icons.Default.Delete,
                                   contentDescription ="Remove",
                                   modifier = Modifier
                                       .padding(
                                           top = 10.dp,
                                           start = 8.dp,
                                           end = 8.dp,
                                       )
                                       .size(24.dp)
                                       .clickable {
                                           showDeleDialog = true
                                           itemToDeleteIndex = index
                                   }
                               )
                           }
                           Divider()
                       }
                   }
                    if(showDeleDialog){
                        val context = LocalContext.current
                        AlertDialog(
                            onDismissRequest = {
                                showDeleDialog = false
                                itemToDeleteIndex = -1
                            },
                            title = {
                                Text(text = "Thông Báo")
                            },
                            text = {
                                Text(text = "Bạn có muốn xoá item này không?")
                            },
                            confirmButton = {
                                Button(onClick = {
                                    names = names.toMutableList().apply {
                                        removeAt(itemToDeleteIndex)
                                    }
                                    showDeleDialog = false
                                    itemToDeleteIndex = -1
                                    Toast.makeText(context, "Item đã được xoá thành công", Toast.LENGTH_SHORT).show()
                                }) {
                                    Text(text = "Yes")
                                }
                            },
                            dismissButton = {
                                Button(onClick = {
                                    showDeleDialog = false
                                    itemToDeleteIndex = -1
                                }) {
                                    Text(text = "No")
                                }
                            },
                        )
                    }
                    if(showEditDialog){
                        val context = LocalContext.current
                        AlertDialog(
                            onDismissRequest = {
                                showEditDialog = false
                                itemToEditIndex = -1
                            },
                            title = { Text(text= "Chỉnh sửa") },
                            text = {
                                Column {
                                Text(text = "Chỉnh sửa item")
                                OutlinedTextField(
                                    value = editItemText,
                                    onValueChange = { editItemText = it },
                                )
                            } },
                            confirmButton = {
                                Button(onClick = {
                                    names = names.toMutableList().apply {
                                        set(itemToEditIndex, editItemText)
                                    }
                                    showEditDialog = false
                                    itemToEditIndex = -1
                                    editItemText = ""
                                    Toast.makeText(context, "Item đã chỉnh sửa thành công", Toast.LENGTH_SHORT).show()
                                }) {
                                    Text(text = "Confirm")
                                }
                            },
                            dismissButton = {
                                Button(onClick = {
                                    showEditDialog = false
                                    itemToEditIndex = -1
                                    editItemText = ""
                                }) {
                                    Text(text = "Cancel")
                                }
                            },
                        )
                    }
               }
            }
        }
    }
}

