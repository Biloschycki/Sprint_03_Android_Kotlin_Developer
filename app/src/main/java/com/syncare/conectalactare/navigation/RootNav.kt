package com.syncare.conectalactare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.syncare.conectalactare.ui.screens.cadastro.CadastroScreen
import com.syncare.conectalactare.ui.screens.doadora.DoadoraConteudoDetalheScreen
import com.syncare.conectalactare.ui.screens.landing.LandingScreen
import com.syncare.conectalactare.ui.screens.login.LoginScreen
import com.syncare.conectalactare.ui.screens.quiz.QuizScreen
import com.syncare.conectalactare.ui.screens.quiz.ResultadoElegivelScreen
import com.syncare.conectalactare.ui.screens.quiz.ResultadoInelegivelScreen

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LANDING) {
        composable(Routes.LANDING) {
            LandingScreen(
                aoClicarQuero = { navController.navigate(Routes.QUIZ) },
                aoClicarJaSouDoadora = { navController.navigate(Routes.LOGIN) }
            )
        }
        composable(Routes.QUIZ) {
            QuizScreen(
                aoConcluir = {
                    val respostas = AppState.respostasQuiz.value
                    val elegivel = respostas.values.count { it } >= 3
                    if (elegivel) navController.navigate(Routes.RESULTADO_ELEGIVEL)
                    else navController.navigate(Routes.RESULTADO_INELEGIVEL)
                },
                aoVoltar = { navController.popBackStack() }
            )
        }
        composable(Routes.RESULTADO_ELEGIVEL) {
            ResultadoElegivelScreen(aoContinuar = { navController.navigate(Routes.CADASTRO) })
        }
        composable(Routes.RESULTADO_INELEGIVEL) {
            ResultadoInelegivelScreen(aoVoltarInicio = {
                navController.navigate(Routes.LANDING) { popUpTo(Routes.LANDING) { inclusive = true } }
            })
        }
        composable(Routes.CADASTRO) {
            CadastroScreen(
                aoConcluir = { navController.navigate(Routes.LOGIN) { popUpTo(Routes.LANDING) { inclusive = false } } },
                aoVoltar = { navController.popBackStack() }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                aoEntrarComoDoadora = {
                    navController.navigate(Routes.DOADORA_INICIO) { popUpTo(Routes.LANDING) { inclusive = true } }
                },
                aoEntrarComoGestor = {
                    navController.navigate(Routes.GESTOR_VISAO_GERAL) { popUpTo(Routes.LANDING) { inclusive = true } }
                }
            )
        }
        composable(Routes.DOADORA_INICIO) {
            DoadoraAreaScreen(aoSair = {
                navController.navigate(Routes.LANDING) { popUpTo(0) }
            })
        }
        composable(Routes.GESTOR_VISAO_GERAL) {
            GestorAreaScreen(aoSair = {
                navController.navigate(Routes.LANDING) { popUpTo(0) }
            })
        }
    }
}