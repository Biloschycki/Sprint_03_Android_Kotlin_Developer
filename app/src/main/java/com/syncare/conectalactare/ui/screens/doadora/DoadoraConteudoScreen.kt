package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.ui.components.ClBadge
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.components.ClTopBar
import com.syncare.conectalactare.ui.components.clickableSafe
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun DoadoraConteudoListScreen(aoAbrirDetalhe: (String) -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Conteúdo educativo", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
        Text("Aprenda a doar com segurança.", fontSize = 12.sp, color = Brand)
        Spacer(Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(MockData.conteudos, key = { it.id }) { c ->
                ClCard(modifier = Modifier.clickableSafe { aoAbrirDetalhe(c.slug) }) {
                    ClBadge(c.categoria)
                    Spacer(Modifier.height(6.dp))
                    Text(c.titulo, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 15.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(c.resumo, fontSize = 12.sp, color = Brand)
                    Spacer(Modifier.height(4.dp))
                    Text("${c.tempoLeituraMin} min de leitura", fontSize = 11.sp, color = Brand)
                }
            }
        }
    }
}

@Composable
fun DoadoraConteudoDetalheScreen(slug: String, aoVoltar: () -> Unit) {
    val conteudo = MockData.conteudos.firstOrNull { it.slug == slug }
    androidx.compose.material3.Scaffold(topBar = { ClTopBar(conteudo?.titulo ?: "Conteúdo", aoVoltar) }) { padding ->
        Column(Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(20.dp)) {
            if (conteudo == null) {
                Text("Conteúdo não encontrado.", color = Brand)
            } else {
                ClBadge(conteudo.categoria)
                Spacer(Modifier.height(10.dp))
                Text(conteudo.titulo, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BrandDark)
                Spacer(Modifier.height(4.dp))
                Text("${conteudo.tempoLeituraMin} min de leitura", fontSize = 12.sp, color = Brand)
                Spacer(Modifier.height(16.dp))
                Text(conteudo.corpo, fontSize = 14.sp, lineHeight = 21.sp, color = BrandDark)
            }
        }
    }
}

