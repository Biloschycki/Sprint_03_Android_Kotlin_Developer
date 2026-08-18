package com.syncare.conectalactare.ui.screens.gestor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.data.model.ItemAvaliacao
import com.syncare.conectalactare.data.model.StatusAvaliacao
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.components.ClOutlineButton
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun GestorAvaliacoesScreen() {
    val fila = remember { mutableStateListOf<ItemAvaliacao>().apply { addAll(MockData.filaAvaliacao) } }

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Fila de avaliação", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Aprove ou reprove cadastros pendentes.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        if (fila.isEmpty()) {
            Text("Nenhum cadastro pendente. 🎉", color = Brand)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(fila, key = { it.id }) { item ->
                    ClCard {
                        Text(item.doadora.nome, fontWeight = FontWeight.Bold, color = BrandDark)
                        Text(item.doadora.email, fontSize = 12.sp, color = Brand)
                        Text("Solicitado em ${item.dataSolicitacao}", fontSize = 11.sp, color = Brand)
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            ClPrimaryButton("Aprovar", modifier = Modifier.weight(1f)) {
                                item.doadora.statusAvaliacao = StatusAvaliacao.APROVADA
                                fila.remove(item)
                            }
                            ClOutlineButton("Reprovar", modifier = Modifier.weight(1f)) {
                                item.doadora.statusAvaliacao = StatusAvaliacao.REPROVADA
                                fila.remove(item)
                            }
                        }
                    }
                }
            }
        }
    }
}