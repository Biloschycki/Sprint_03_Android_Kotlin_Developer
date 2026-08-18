package com.syncare.conectalactare.navigation
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.sp
import com.syncare.conectalactare.ui.screens.gestor.*
import com.syncare.conectalactare.ui.theme.Brand

private data class ItemNav(val rota: String, val titulo: String, val icone: androidx.compose.ui.graphics.vector.ImageVector)

private val itensGestor = listOf(
    BottomNavItem(Routes.GESTOR_VISAO_GERAL, "Visão geral", Icons.Filled.Dashboard),
    BottomNavItem(Routes.GESTOR_AVALIACOES, "Avaliações", Icons.Filled.FactCheck),
    BottomNavItem(Routes.GESTOR_DOADORAS, "Doadoras", Icons.Filled.People),
    BottomNavItem(Routes.GESTOR_COLETAS, "Coletas", Icons.Filled.WaterDrop),
    BottomNavItem(Routes.GESTOR_AUDITORIA, "Auditoria", Icons.Filled.History)
)

@Composable
fun GestorAreaScreen(aoSair: () -> Unit) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = androidx.compose.ui.graphics.Color.White) {
                itensGestor.forEach { item ->
                    val selecionado = rotaAtual?.hierarchy?.any { it.route == item.rota } == true
                    NavigationBarItem(
                        selected = selecionado,
                        onClick = {
                            navController.navigate(item.rota) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icone, contentDescription = item.titulo) },
                        label = { Text(item.titulo, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = Brand, selectedTextColor = Brand)
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.GESTOR_VISAO_GERAL,
            modifier = androidx.compose.ui.Modifier.padding(padding)
        ) {
            composable(Routes.GESTOR_VISAO_GERAL) { GestorVisaoGeralScreen() }
            composable(Routes.GESTOR_AVALIACOES) { GestorAvaliacoesScreen() }
            composable(Routes.GESTOR_DOADORAS) { GestorDoadorasScreen() }
            composable(Routes.GESTOR_COLETAS) { GestorColetasScreen() }
            composable(Routes.GESTOR_CONTEUDO) { GestorConteudoScreen() }
            composable(Routes.GESTOR_AUDITORIA) { GestorAuditoriaScreen() }
        }
    }
}