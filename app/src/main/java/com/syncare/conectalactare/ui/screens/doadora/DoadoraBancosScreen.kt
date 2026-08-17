package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
fun DoadoraBancosScreen() {
    val bancos = MockData.bancos.sortedBy { it.distanciaKm }
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Bancos de leite próximos", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Encontre onde doar perto de você.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(bancos, key = { it.id }) { b ->
                ClCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(b.nome, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 14.sp, modifier = Modifier.weight(1f))
                        ClBadge("${b.distanciaKm} km")
                    }
                    Spacer(Modifier.height(6.dp))
                    Text("${b.endereco}, ${b.numero} — ${b.bairro}, ${b.cidade}/${b.estado}", fontSize = 12.sp, color = Brand)
                    Text(b.horarioFuncionamento, fontSize = 12.sp, color = Brand)
                    Text(b.telefone, fontSize = 12.sp, color = Brand)
                }
            }
        }
    }
}
