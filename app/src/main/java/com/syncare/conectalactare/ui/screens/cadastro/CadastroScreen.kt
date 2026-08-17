package com.syncare.conectalactare.ui.screens.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.components.ClTopBar
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

@Composable
fun CadastroScreen(aoConcluir: () -> Unit, aoVoltar: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var enviando by remember { mutableStateOf(false) }
    var mostrarSucesso by remember { mutableStateOf(false) }

    fun buscarCepMock() {
        // Simula a integração com ViaCEP feita no frontend web, mas 100% mockada aqui.
        if (cep.length >= 8) {
            endereco = "Av. Paulista"
            cidade = "São Paulo"
            estado = "SP"
        }
    }

    Scaffold(topBar = { ClTopBar("Criar minha conta", aoVoltar) }) { padding ->
        if (mostrarSucesso) {
            Column(
                Modifier.padding(padding).fillMaxSize().padding(28.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Cadastro enviado! 💙", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark, textAlign = TextAlign.Center)
                Spacer(Modifier.height(10.dp))
                Text(
                    "Seus dados foram registrados (mock). Nossa equipe de saúde avaliará seu " +
                            "cadastro antes da liberação das doações.",
                    textAlign = TextAlign.Center, color = Brand, fontSize = 14.sp
                )
                Spacer(Modifier.height(24.dp))
                ClPrimaryButton("Ir para o login") { aoConcluir() }
            }
        } else {
            Column(
                Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(20.dp)
            ) {
                Text(
                    "Leva menos de 3 minutos. Seus dados ficam protegidos pela LGPD.",
                    color = Brand, fontSize = 13.sp
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(nome, { nome = it }, label = { Text("Nome completo") }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(email, { email = it }, label = { Text("E-mail") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(cpf, { cpf = it }, label = { Text("CPF") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(telefone, { telefone = it }, label = { Text("Celular") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    cep, { cep = it; buscarCepMock() }, label = { Text("CEP") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = { Text("Endereço preenchido automaticamente (mock ViaCEP)") }
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(endereco, { endereco = it }, label = { Text("Endereço") }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp))
                Row {
                    OutlinedTextField(cidade, { cidade = it }, label = { Text("Cidade") }, modifier = Modifier.weight(2f))
                    Spacer(Modifier.width(8.dp))
                    OutlinedTextField(estado, { estado = it }, label = { Text("UF") }, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(24.dp))
                ClPrimaryButton("Concluir cadastro", habilitado = nome.isNotBlank() && email.isNotBlank() && !enviando) {
                    enviando = true
                    mostrarSucesso = true
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}