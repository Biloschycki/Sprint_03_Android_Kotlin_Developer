package com.syncare.conectalactare.ui.screens.gestor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.data.model.StatusAvaliacao
import com.syncare.conectalactare.data.model.StatusColeta
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun GestorVisaoGeralScreen() {
    val doadoras = MockData.todasDoadoras()
    val aprovadas = doadoras.count { it.statusAvaliacao == StatusAvaliacao.APROVADA }
    val pendentes = doadoras.count { it.statusAvaliacao == StatusAvaliacao.PENDENTE }
    val litros = MockData.totalLitrosPlataforma()
    val coletasConcluidas = MockData.coletas.count { it.status == StatusColeta.CONCLUIDA }
    val conversao = (aprovadas.toDouble() / doadoras.size * 100).toInt()

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Text("Visão geral", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Indicadores da plataforma (dados mockados).", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ClCard(Modifier.weight(1f)) {
                Text("%.1f L".format(litros), fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = Brand)
                Text("total coletado", fontSize = 11.sp, color = Brand)
            }
            ClCard(Modifier.weight(1f)) {
                Text("$coletasConcluidas", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
                Text("coletas concluídas", fontSize = 11.sp, color = BrandDark)
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ClCard(Modifier.weight(1f)) {
                Text("$conversao%", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
                Text("taxa de conversão", fontSize = 11.sp, color = BrandDark)
            }
            ClCard(Modifier.weight(1f)) {
                Text("$pendentes", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
                Text("aguardando avaliação", fontSize = 11.sp, color = BrandDark)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Doadoras por status", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = BrandDark)
        Spacer(Modifier.height(10.dp))
        StatusAvaliacao.entries.forEach { status ->
            val qtd = doadoras.count { it.statusAvaliacao == status }
            val frac = if (doadoras.isNotEmpty()) qtd / doadoras.size.toFloat() else 0f
            Column(Modifier.padding(vertical = 6.dp)) {
                Text("${status.label} — $qtd", fontSize = 13.sp, color = BrandDark)
                Spacer(Modifier.height(4.dp))
                androidx.compose.material3.LinearProgressIndicator(
                    progress = frac,
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                    color = Brand
                )
            }
        }
        Spacer(Modifier.height(20.dp))
    }
}