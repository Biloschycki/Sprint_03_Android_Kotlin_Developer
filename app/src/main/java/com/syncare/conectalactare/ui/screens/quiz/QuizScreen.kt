package com.syncare.conectalactare.ui.screens.quiz
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.mock.MockData
import com.syncare.conectalactare.navigation.AppState
import com.syncare.conectalactare.ui.components.ClOutlineButton
import com.syncare.conectalactare.ui.components.ClPrimaryButton
import com.syncare.conectalactare.ui.components.ClTopBar
import com.syncare.conectalactare.ui.theme.Brand
import com.syncare.conectalactare.ui.theme.BrandDark
import com.syncare.conectalactare.ui.theme.BrandLight

@Composable
fun QuizScreen(aoConcluir: () -> Unit, aoVoltar: () -> Unit) {
    val perguntas = MockData.perguntasQuiz
    var indice by remember { mutableStateOf(0) }
    val respostas = remember { mutableStateMapOf<Long, Boolean>() }

    fun responder(valor: Boolean) {
        respostas[perguntas[indice].id] = valor
        if (indice + 1 < perguntas.size) {
            indice++
        } else {
            AppState.respostasQuiz.value = respostas.toMap()
            aoConcluir()
        }
    }

    Scaffold(topBar = { ClTopBar("Quiz de elegibilidade", aoVoltar) }) { padding ->
        Column(Modifier.padding(padding).padding(20.dp)) {
            Text("Pergunta ${indice + 1} de ${perguntas.size}", color = Brand, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = (indice + 1) / perguntas.size.toFloat(),
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(999.dp)),
                color = Brand,
                trackColor = BrandLight
            )
            Spacer(Modifier.height(28.dp))
            Text(perguntas[indice].texto, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = BrandDark)
            Spacer(Modifier.height(28.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ClPrimaryButton("Sim", modifier = Modifier.weight(1f)) { responder(true) }
                ClOutlineButton("Não", modifier = Modifier.weight(1f)) { responder(false) }
            }
            if (indice > 0) {
                Spacer(Modifier.height(16.dp))
                TextButton(onClick = { indice-- }) { Text("Voltar") }
            }
        }
    }
}
