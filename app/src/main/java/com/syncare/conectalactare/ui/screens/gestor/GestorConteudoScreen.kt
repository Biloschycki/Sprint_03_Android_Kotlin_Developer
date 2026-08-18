package com.syncare.conectalactare.ui.screens.gestor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.ui.components.ClBadge
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun GestorConteudoScreen() {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Conteúdo educativo (CMS)", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Gestão de artigos exibidos às doadoras.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(MockData.conteudos, key = { it.id }) { c ->
                ClCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.weight(1f)) {
                            Text(c.titulo, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 14.sp)
                            Text("${c.tempoLeituraMin} min de leitura", fontSize = 12.sp, color = Brand)
                        }
                        ClBadge(c.categoria)
                    }
                }
            }
        }
    }
}