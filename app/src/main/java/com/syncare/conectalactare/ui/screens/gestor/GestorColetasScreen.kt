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
fun GestorColetasScreen() {
    val coletas = MockData.coletas.sortedByDescending { it.dataAgendada }
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Coletas da plataforma", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Todas as coletas sinalizadas pelas doadoras.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(coletas, key = { it.id }) { c ->
                ClCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(Modifier.weight(1f)) {
                            Text(c.banco.nome, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 14.sp)
                            Text(c.dataAgendada.replace("T", " "), fontSize = 12.sp, color = Brand)
                            Text(c.modalidade.label, fontSize = 12.sp, color = Brand)
                        }
                        ClBadge(c.status.label, c.status.toBadgeTone())
                    }
                }
            }
        }
    }
}