package com.devtanmay.dictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devtanmay.dictionaryapp.feature_dictionary.presentation.WordInfoItem
import com.devtanmay.dictionaryapp.feature_dictionary.presentation.WordInfoVIewModel
import com.devtanmay.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                // A surface container using the 'background' color from the theme
               val viewmodel:WordInfoVIewModel= hiltViewModel()
                val state=viewmodel.state.value
                val snackbarHostState = remember { SnackbarHostState() }
                LaunchedEffect(key1 = true){
viewmodel.eventFlow.collectLatest {
    event->
    when(event){

        is WordInfoVIewModel.UIEvent.ShowSnackbar->{
            snackbarHostState.showSnackbar(
                message = event.message
            )
        }
    }
}

                }

                Scaffold(topBar = {
                    Text(text = "Dictionary")
                }, content = {

                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {


                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                            TextField(
                               value = viewmodel.searchQuery.value,
                               onValueChange =  viewmodel::onSearch,
placeholder = { Text(text = "Search... ")},
                                modifier = Modifier.fillMaxWidth()
                                    .clip(shape= RoundedCornerShape(16))
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            LazyColumn(modifier = Modifier.fillMaxSize()){
                                items(state.wordInfoItems.size){
                                    i->
                                    val wordInfo=state.wordInfoItems[i]
                                    if (i>0){
                                        Spacer(modifier = Modifier.height(8.dp))}
                                        WordInfoItem(wordInfo=wordInfo)
                                            if(i<state.wordInfoItems.size-1){
                                                Divider()
                                            }



                                    }

                                }
                            }

                        }

                    })
                }


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
    DictionaryAppTheme {
        Greeting("Android")
    }
}