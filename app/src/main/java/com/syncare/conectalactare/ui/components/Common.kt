package com.syncare.conectalactare.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.data.model.StatusAvaliacao
import com.syncare.conectalactare.data.model.StatusColeta
import com.syncare.conectalactare.ui.theme.*

enum class BadgeTone { BRAND, SUCCESS, WARNING, DANGER, NEUTRAL }

@Composable
fun ClBadge(texto: String, tone: BadgeTone = BadgeTone.BRAND) {
    val (bg, fg) = when (tone) {
        BadgeTone.BRAND -> BrandLight to BrandDark
        BadgeTone.SUCCESS -> Color(0xFFDCFCE7) to Success
        BadgeTone.WARNING -> Color(0xFFFEF3C7) to Warning
        BadgeTone.DANGER -> Color(0xFFFEE2E2) to Danger
        BadgeTone.NEUTRAL -> Color(0xFFF1F5F9) to SlateMuted
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(texto, color = fg, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

fun StatusAvaliacao.toBadgeTone() = when (this) {
    StatusAvaliacao.APROVADA -> BadgeTone.SUCCESS
    StatusAvaliacao.PENDENTE -> BadgeTone.WARNING
    StatusAvaliacao.REPROVADA -> BadgeTone.DANGER
}

fun StatusColeta.toBadgeTone() = when (this) {
    StatusColeta.CONCLUIDA -> BadgeTone.SUCCESS
    StatusColeta.AGENDADA -> BadgeTone.BRAND
    StatusColeta.EM_COLETA -> BadgeTone.WARNING
    StatusColeta.PROBLEMA -> BadgeTone.DANGER
    StatusColeta.CANCELADA -> BadgeTone.NEUTRAL
}

@Composable
fun ClPrimaryButton(
    texto: String,
    modifier: Modifier = Modifier,
    habilitado: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        modifier = modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Brand, contentColor = Color.White)
    ) {
        Text(texto, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun ClOutlineButton(
    texto: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(texto, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun ClCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(16.dp), content = content)
    }
}

@Composable
fun ClTopBar(titulo: String, aoVoltar: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(titulo, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            if (aoVoltar != null) {
                IconButton(onClick = aoVoltar) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BrandDark,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

fun Modifier.clickableSafe(onClick: () -> Unit): Modifier = this.clickable(onClick = onClick)

@Composable
fun Iniciais(nome: String, tamanho: Int = 56) {
    val iniciais = nome.split(" ").filter { it.isNotBlank() }.take(2).joinToString("") { it.first().uppercase() }
    Box(
        modifier = Modifier.size(tamanho.dp).clip(CircleShape).background(Brand),
        contentAlignment = Alignment.Center
    ) {
        Text(iniciais, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = (tamanho / 2.8).sp)
    }
}