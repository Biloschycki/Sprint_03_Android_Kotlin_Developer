package com.syncare.conectalactare.navigation

import androidx.compose.runtime.mutableStateOf
import com.syncare.conectalactare.data.model.PapelUsuario

object AppState {
    var papelLogado = mutableStateOf<PapelUsuario?>(null)
    var respostasQuiz = mutableStateOf<Map<Long, Boolean>>(emptyMap())
}