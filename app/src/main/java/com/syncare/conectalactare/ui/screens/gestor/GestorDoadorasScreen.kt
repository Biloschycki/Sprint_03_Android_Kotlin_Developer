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
import com.syncare.conectalactare.ui.components.toBadgeTone
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun GestorDoadorasScreen() {
    val doadoras = MockData.todasDoadoras()
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Doadoras", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("${doadoras.size} cadastradas na plataforma.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(doadoras, key = { it.id }) { d ->
                ClCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.weight(1f)) {
                            Text(d.nome, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 14.sp)
                            Text(d.email, fontSize = 12.sp, color = Brand)
                            Text("${d.cidade}/${d.estado}", fontSize = 12.sp, color = Brand)
                        }
                        ClBadge(d.statusAvaliacao.label, d.statusAvaliacao.toBadgeTone())
                    }
                }
            }
        }
    }
}