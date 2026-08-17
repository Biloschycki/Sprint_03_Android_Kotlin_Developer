package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.data.model.StatusColeta
import com.syncare.conectalactare.ui.components.*
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

val CANCELAVEIS = setOf(StatusColeta.AGENDADA, StatusColeta.EM_COLETA, StatusColeta.PROBLEMA)

@Composable
fun DoadoraColetasScreen(aoNovaColeta: () -> Unit) {
    var versao by remember { mutableStateOf(0) } // força recomposição após cancelar
    val doadora = MockData.doadoraAtual
    val coletas = MockData.coletas.filter { it.doadoraId == doadora.id }
        .sortedByDescending { it.dataAgendada }
    val concluidas = coletas.filter { it.status == StatusColeta.CONCLUIDA }
    val totalL = concluidas.sumOf { it.volumeMl ?: 0 } / 1000.0
    var confirmandoId by remember { mutableStateOf<Long?>(null) }

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text("Minhas coletas", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
                Text("Acompanhe suas doações.", fontSize = 12.sp, color = Brand)
            }
        }
        Spacer(Modifier.height(12.dp))
        ClPrimaryButton("+ Sinalizar coleta") { aoNovaColeta() }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ClCard(Modifier.weight(1f)) {
                Text("%.1f L".format(totalL), fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BrandDark)
                Text("total doado", fontSize = 11.sp, color = Brand)
            }
            ClCard(Modifier.weight(1f)) {
                Text("${concluidas.size}", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BrandDark)
                Text("concluídas", fontSize = 11.sp, color = Brand)
            }
        }
        Spacer(Modifier.height(16.dp))

        if (coletas.isEmpty()) {
            Text("Você ainda não tem coletas.", color = Brand)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(coletas, key = { it.id }) { coleta ->
                    ClCard {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text(coleta.banco.nome, fontWeight = FontWeight.Bold, color = BrandDark)
                                Text("${coleta.banco.cidade}/${coleta.banco.estado}", fontSize = 12.sp, color = Brand)
                            }
                            ClBadge(coleta.status.label, coleta.status.toBadgeTone())
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("Data prevista: ${coleta.dataAgendada.replace("T", " ")}", fontSize = 12.sp, color = BrandDark)
                        Text("Modalidade: ${coleta.modalidade.label}", fontSize = 12.sp, color = BrandDark)
                        coleta.volumeMl?.let { Text("Volume: $it ml", fontSize = 12.sp, color = BrandDark) }

                        if (coleta.status in CANCELAVEIS) {
                            Spacer(Modifier.height(10.dp))
                            if (confirmandoId == coleta.id) {
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    ClOutlineButton("Confirmar cancelamento", modifier = Modifier.weight(1f)) {
                                        coleta.status = StatusColeta.CANCELADA
                                        confirmandoId = null
                                        versao++
                                    }
                                    TextButton(onClick = { confirmandoId = null }) { Text("Voltar") }
                                }
                            } else {
                                ClOutlineButton("Cancelar coleta") { confirmandoId = coleta.id }
                            }
                        }
                    }
                }
            }
        }
    }
}