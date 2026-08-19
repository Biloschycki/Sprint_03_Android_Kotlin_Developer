package com.syncare.conectalactare.ui.screens.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.ui.components.ClCard
import com.syncare.conectalactare.ui.components.ClOutlineButton
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.theme.*

@Composable
fun LandingScreen(
    aoClicarQuero: () -> Unit,
    aoClicarJaSouDoadora: () -> Unit,
    aoClicarSouGestor: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero
        Column(
            Modifier
                .fillMaxWidth()
                .background(BrandDark)
                .padding(24.dp)
        ) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Icon(Icons.Filled.Favorite, contentDescription = null, tint = BrandLight)
                Spacer(Modifier.width(8.dp))
                Text("Rede de doação de leite humano", color = BrandLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "Cada gota importa.\nCada conexão salva vidas.",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                lineHeight = 36.sp
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Seu leite pode alimentar bebês prematuros que dependem dele para sobreviver. " +
                        "Descubra em 2 minutos se você pode doar.",
                color = BrandLight,
                fontSize = 15.sp,
                lineHeight = 21.sp
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = aoClicarQuero,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Brand),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) { Text("Quero doar", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.height(10.dp))
            OutlinedButton(
                onClick = aoClicarJaSouDoadora,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) { Text("Já sou doadora", fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.height(10.dp))
            TextButton(
                onClick = aoClicarSouGestor,
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) { Text("Sou gestor(a) de banco de leite", fontWeight = FontWeight.Medium, fontSize = 13.sp) }
        }

        // Stats
        Row(
            Modifier
                .fillMaxWidth()
                .background(BrandLight)
                .padding(vertical = 20.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("237" to "bancos de leite", "1,6M" to "nutrizes/ano", "245 mil" to "litros em 2024").forEach { (valor, rotulo) ->
                Column(Modifier.weight(1f)) {
                    Text(valor, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BrandDark)
                    Text(rotulo, fontSize = 11.sp, color = SlateMuted)
                }
            }
        }

        // Como funciona
        Column(Modifier.padding(20.dp)) {
            Text("Como funciona", fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
            Spacer(Modifier.height(12.dp))
            listOf(
                "1. Responda o quiz" to "Descubra em 2 minutos se você pode doar.",
                "2. Crie sua conta" to "Cadastro rápido e protegido.",
                "3. Encontre um banco" to "Veja os bancos de leite mais próximos.",
                "4. Sinalize a coleta" to "Em casa ou no banco, do seu jeito.",
                "5. Doe e acompanhe" to "Veja o impacto de cada gota."
            ).forEach { (titulo, texto) ->
                Row(Modifier.padding(vertical = 6.dp)) {
                    Column {
                        Text(titulo, fontWeight = FontWeight.Bold, color = BrandDark, fontSize = 15.sp)
                        Text(texto, color = SlateMuted, fontSize = 13.sp)
                    }
                }
            }
        }

        // Por que doar
        Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
            ClCard {
                Text("10", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = Brand)
                Text(
                    "bebês podem ser alimentados por dia com apenas 1 litro de leite doado",
                    color = SlateMuted, fontSize = 13.sp
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}