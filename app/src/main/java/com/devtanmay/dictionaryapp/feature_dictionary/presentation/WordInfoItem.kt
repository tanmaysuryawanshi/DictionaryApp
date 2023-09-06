package com.devtanmay.dictionaryapp.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.devtanmay.dictionaryapp.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(wordInfo: WordInfo,modifier:Modifier=Modifier) {
    Column(Modifier) {
Text(text = wordInfo.word,
    style = MaterialTheme.typography.displayLarge,
    color = MaterialTheme.colorScheme.primary
)
        Text(text = wordInfo.phonetic,
        fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))
       wordInfo.meanings.forEach { meaning ->  
           Text(text = meaning.partOfSpeech,
           style=MaterialTheme.typography.titleLarge,
           color = MaterialTheme.colorScheme.secondary)
           Spacer(modifier = Modifier.height(8.dp))
           meaning.definitions.forEachIndexed {index,definition->
               Text(text = "${index+1}. ${definition.definition}",
                   style=MaterialTheme.typography.bodyMedium)
               Spacer(modifier = Modifier.height(4.dp))
                definition.example?.let {
                    Text(text = "Example: $it",
                        style=MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

           }
           
       }
    }
}