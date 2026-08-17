package com.syncare.conectalactare.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark
import com.syncare.conectalactare.ui.theme.Success

@Composable
fun ResultadoElegivelScreen(aoContinuar: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Success, modifier = Modifier.size(72.dp))
        Spacer(Modifier.height(20.dp))
        Text(
            "Você pode ser uma doadora!",
            fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = BrandDark, textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "Com base nas suas respostas, você segue para o cadastro. Depois, nossa equipe de " +
                    "saúde faz uma avaliação antes da liberação das doações.",
            textAlign = TextAlign.Center, color = Brand, fontSize = 14.sp
        )
        Spacer(Modifier.height(28.dp))
        ClPrimaryButton("Continuar para o cadastro") { aoContinuar() }
    }
}

@Composable
fun ResultadoInelegivelScreen(aoVoltarInicio: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Filled.Info, contentDescription = null, tint = Brand, modifier = Modifier.size(72.dp))
        Spacer(Modifier.height(20.dp))
        Text(
            "Por enquanto você não pode doar",
            fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = BrandDark, textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(10.dp))
        Text(
            "Isso pode mudar no futuro. Enquanto isso, você pode conhecer nosso conteúdo " +
                    "educativo sobre doação de leite humano.",
            textAlign = TextAlign.Center, color = Brand, fontSize = 14.sp
        )
        Spacer(Modifier.height(28.dp))
        ClPrimaryButton("Voltar para o início") { aoVoltarInicio() }
    }
}