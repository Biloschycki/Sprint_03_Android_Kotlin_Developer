package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.data.model.BancoLeite
import com.syncare.conectalactare.data.model.Coleta
import com.syncare.conectalactare.data.model.ModalidadeColeta
import com.syncare.conectalactare.data.model.StatusColeta
import com.syncare.conectalactare.ui.components.*
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark
import com.syncare.conectalactare.ui.theme.BrandLight

@Composable
fun DoadoraNovaColetaScreen(aoConfirmar: () -> Unit, aoVoltar: () -> Unit) {
    val doadora = MockData.doadoraAtual
    val bancos = MockData.bancos
    var bancoSelecionado by remember { mutableStateOf<BancoLeite?>(null) }
    var modalidade by remember { mutableStateOf<ModalidadeColeta?>(null) }

    Scaffold(topBar = { ClTopBar("Sinalizar leite excedente", aoVoltar) }) { padding ->
        Column(Modifier.padding(padding).padding(20.dp)) {
            Text(
                "Escolha um banco de leite próximo. A coleta será agendada para o seu dia fixo de doação.",
                fontSize = 13.sp, color = Brand
            )
            Spacer(Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
                items(bancos, key = { it.id }) { banco ->
                    val selecionado = banco.id == bancoSelecionado?.id
                    ClCard(
                        modifier = Modifier
                            .clickableSafe { bancoSelecionado = banco; modalidade = null }
                            .border(
                                width = if (selecionado) 2.dp else 1.dp,
                                color = if (selecionado) Brand else BrandLight,
                                shape = RoundedCornerShape(18.dp)
                            )
                    ) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(Modifier.weight(1f)) {
                                Text(banco.nome, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 14.sp)
                                Text("${banco.endereco}, ${banco.numero} — ${banco.cidade}/${banco.estado}", fontSize = 12.sp, color = Brand)
                                Text(banco.horarioFuncionamento, fontSize = 12.sp, color = Brand)
                            }
                            ClBadge("${banco.distanciaKm} km")
                        }
                    }
                }
            }

            if (bancoSelecionado != null) {
                Spacer(Modifier.height(12.dp))
                Text("Modalidade", fontWeight = FontWeight.Bold, color = BrandDark)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    bancoSelecionado!!.modalidades.forEach { m ->
                        val sel = m == modalidade
                        Box(
                            Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.dp, if (sel) Brand else BrandLight, RoundedCornerShape(12.dp))
                                .clickableSafe { modalidade = m }
                                .padding(12.dp)
                        ) {
                            Text(m.label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = if (sel) Brand else BrandDark)
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            ClPrimaryButton("Confirmar sinalização", habilitado = bancoSelecionado != null && modalidade != null) {
                val novoId = (MockData.coletas.maxOfOrNull { it.id } ?: 0) + 1
                MockData.coletas.add(
                    Coleta(
                        id = novoId,
                        doadoraId = doadora.id,
                        banco = bancoSelecionado!!,
                        dataAgendada = "2026-08-26T09:00:00",
                        modalidade = modalidade!!,
                        status = StatusColeta.AGENDADA
                    )
                )
                aoConfirmar()
            }
        }
    }
}
