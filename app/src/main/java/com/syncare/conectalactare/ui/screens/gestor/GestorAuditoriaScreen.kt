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
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun GestorAuditoriaScreen() {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Auditoria", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Histórico de ações realizadas na plataforma.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(MockData.auditoria, key = { it.id }) { log ->
                ClCard {
                    Text("${log.autor} — ${log.acao}", fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 13.sp)
                    Text(log.alvo, fontSize = 12.sp, color = Brand)
                    Text(log.dataHora, fontSize = 11.sp, color = Brand)
                }
            }
        }
    }
}