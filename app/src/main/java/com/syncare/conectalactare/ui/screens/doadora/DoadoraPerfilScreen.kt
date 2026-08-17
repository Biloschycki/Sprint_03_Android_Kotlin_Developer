package com.syncare.conectalactare.ui.screens.doadora

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.ui.components.*
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

private val DIAS = listOf("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado")

@Composable
fun DoadoraPerfilScreen() {
    val doadora = MockData.doadoraAtual
    var nome by remember { mutableStateOf(doadora.nome) }
    var email by remember { mutableStateOf(doadora.email) }
    var telefone by remember { mutableStateOf(doadora.telefone) }
    var diaColeta by remember { mutableStateOf(doadora.diaColetaSemana ?: 3) }
    var salvo by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Iniciais(doadora.nome)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(doadora.nome, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = BrandDark)
                Text("Doadora desde ${doadora.doadoraDesde}", fontSize = 12.sp, color = Brand)
            }
            Spacer(Modifier.weight(1f))
            ClBadge(doadora.statusAvaliacao.label, doadora.statusAvaliacao.toBadgeTone())
        }

        Spacer(Modifier.height(24.dp))
        OutlinedTextField(nome, { nome = it }, label = { Text("Nome completo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(email, { email = it }, label = { Text("E-mail") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(telefone, { telefone = it }, label = { Text("Celular") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField("${doadora.cidade}/${doadora.estado} · CEP ${doadora.cep}", {}, readOnly = true,
            label = { Text("Endereço") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))
        Text("Dia de coleta", fontWeight = FontWeight.Bold, color = BrandDark)
        Spacer(Modifier.height(8.dp))
        // Seletor simples via botões (evita dependências extras de dropdown)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            DIAS.forEachIndexed { i, dia ->
                val sel = i == diaColeta
                if (sel) {
                    Button(onClick = { diaColeta = i }, modifier = Modifier.weight(1f), contentPadding = PaddingValues(4.dp)) {
                        Text(dia.take(3), fontSize = 10.sp)
                    }
                } else {
                    OutlinedButton(onClick = { diaColeta = i }, modifier = Modifier.weight(1f), contentPadding = PaddingValues(4.dp)) {
                        Text(dia.take(3), fontSize = 10.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        ClPrimaryButton("Salvar alterações") {
            doadora.diaColetaSemana = diaColeta
            salvo = true
        }
        if (salvo) {
            Spacer(Modifier.height(10.dp))
            Text("Perfil atualizado com sucesso!", color = com.syncare.conectalactare.ui.theme.Success, fontSize = 13.sp)
        }
        Spacer(Modifier.height(20.dp))
    }
}