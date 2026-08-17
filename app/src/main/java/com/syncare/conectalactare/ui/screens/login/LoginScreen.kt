package com.syncare.conectalactare.ui.screens.login;

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.model.PapelUsuario
import com.syncare.conectalactare.navigation.AppState
import com.syncare.conectalactare.ui.components.ClOutlineButton
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark

/**
 * Tela de login mockada. Como não há autenticação real nesta sprint, qualquer
 * e-mail/senha é aceito e o usuário escolhe qual área quer demonstrar.
 */
@Composable
fun LoginScreen(
    aoEntrarComoDoadora: () -> Unit,
    aoEntrarComoGestor: () -> Unit
) {
    var email by remember { mutableStateOf("mariana.silva@email.com") }
    var senha by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(28.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bem-vinda de volta", fontWeight = FontWeight.ExtraBold, fontSize = 26.sp, color = BrandDark)
        Spacer(Modifier.height(4.dp))
        Text("Entre para acompanhar suas doações.", color = Brand, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(email, { email = it }, label = { Text("E-mail") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            senha, { senha = it }, label = { Text("Senha") }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
        )
        Spacer(Modifier.height(24.dp))
        ClPrimaryButton("Entrar como doadora") {
            AppState.papelLogado.value = PapelUsuario.DOADORA
            aoEntrarComoDoadora()
        }
        Spacer(Modifier.height(10.dp))
        ClOutlineButton("Entrar como gestor(a)") {
            AppState.papelLogado.value = PapelUsuario.GESTOR
            aoEntrarComoGestor()
        }
        Spacer(Modifier.height(16.dp))
        Text(
            "Demonstração: qualquer e-mail/senha entra. Escolha o perfil para navegar pelo MVP.",
            fontSize = 12.sp, color = Brand, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
        )
    }
}


