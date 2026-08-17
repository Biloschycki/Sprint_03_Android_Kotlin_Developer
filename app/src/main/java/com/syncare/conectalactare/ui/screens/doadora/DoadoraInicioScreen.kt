package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.data.model.StatusColeta
import com.syncare.conectalactare.ui.components.*
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun DoadoraInicioScreen(aoAbrirColetas: () -> Unit, aoAbrirNovaColeta: () -> Unit, aoAbrirConteudo: () -> Unit) {
    val doadora = MockData.doadoraAtual
    val coletas = MockData.coletas.filter { it.doadoraId == doadora.id }
    val concluidas = coletas.filter { it.status == StatusColeta.CONCLUIDA }
    val totalL = concluidas.sumOf { it.volumeMl ?: 0 } / 1000.0
    val proxima = coletas.filter { it.status == StatusColeta.AGENDADA }.minByOrNull { it.dataAgendada }
    val primeiroNome = doadora.nome.split(" ").first()

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text("Olá, $primeiroNome 💙", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
            ClBadge(doadora.statusAvaliacao.label, doadora.statusAvaliacao.toBadgeTone())
        }
        Spacer(Modifier.height(16.dp))

        // Resumo de impacto
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ClCard(Modifier.weight(1f)) {
                Text("%.1f L".format(totalL), fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = Brand)
                Text("doados até agora", fontSize = 11.sp, color = Brand)
            }
            ClCard(Modifier.weight(1f)) {
                Text("${concluidas.size}", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
                Text("doações concluídas", fontSize = 11.sp, color = BrandDark)
            }
        }
        Spacer(Modifier.height(8.dp))
        ClCard {
            Text("${concluidas.size * 10}", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
            Text("bebês potencialmente alimentados", fontSize = 12.sp, color = BrandDark)
        }

        Spacer(Modifier.height(20.dp))
        Text("Próxima coleta", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BrandDark)
        Spacer(Modifier.height(8.dp))
        if (proxima != null) {
            ClCard(modifier = Modifier.clickableSafe(aoAbrirColetas)) {
                Text(proxima.banco.nome, fontWeight = FontWeight.Bold, color = BrandDark)
                Text(proxima.dataAgendada.replace("T", " "), fontSize = 13.sp, color = Brand)
            }
        } else {
            ClCard {
                Text("Nenhuma coleta agendada", fontWeight = FontWeight.Bold, color = BrandDark)
                Spacer(Modifier.height(4.dp))
                Text("Sinalize quando tiver leite excedente.", fontSize = 13.sp, color = Brand)
                Spacer(Modifier.height(10.dp))
                ClPrimaryButton("Sinalizar coleta") { aoAbrirNovaColeta() }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Atalhos", fontWeight = FontWeight.Bold, fontSize = 17.sp, color = BrandDark)
        Spacer(Modifier.height(8.dp))
        ClCard(modifier = Modifier.clickableSafe(aoAbrirNovaColeta)) { Text("💧  Sinalizar coleta", fontWeight = FontWeight.Bold, color = BrandDark) }
        Spacer(Modifier.height(8.dp))
        ClCard(modifier = Modifier.clickableSafe(aoAbrirConteudo)) { Text("📚  Conteúdo educativo", fontWeight = FontWeight.Bold, color = BrandDark) }
        Spacer(Modifier.height(20.dp))
    }
}