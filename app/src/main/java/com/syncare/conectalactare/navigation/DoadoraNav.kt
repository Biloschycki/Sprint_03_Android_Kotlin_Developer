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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.syncare.conectalactare.ui.components.ClTopBar
import com.syncare.conectalactare.ui.screens.doadora.*
import com.syncare.conectalactare.ui.theme.Brand


private val itensDoadora = listOf(
    BottomNavItem(Routes.DOADORA_INICIO, "Início", Icons.Filled.Home),
    BottomNavItem(Routes.DOADORA_COLETAS, "Coletas", Icons.Filled.WaterDrop),
    BottomNavItem(Routes.DOADORA_BANCOS, "Bancos", Icons.Filled.Place),
    BottomNavItem(Routes.DOADORA_CONTEUDO, "Conteúdo", Icons.Filled.MenuBook),
    BottomNavItem(Routes.DOADORA_PERFIL, "Perfil", Icons.Filled.Person)
)

@Composable
fun DoadoraAreaScreen(aoSair: () -> Unit) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = androidx.compose.ui.graphics.Color.White) {
                itensDoadora.forEach { item ->
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
                        label = { Text(item.titulo) },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = Brand, selectedTextColor = Brand)
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.DOADORA_INICIO,
            modifier = androidx.compose.ui.Modifier.padding(padding)
        ) {
            composable(Routes.DOADORA_INICIO) {
                DoadoraInicioScreen(
                    aoAbrirColetas = { navController.navigate(Routes.DOADORA_COLETAS) },
                    aoAbrirNovaColeta = { navController.navigate(Routes.DOADORA_NOVA_COLETA) },
                    aoAbrirConteudo = { navController.navigate(Routes.DOADORA_CONTEUDO) }
                )
            }
            composable(Routes.DOADORA_COLETAS) {
                DoadoraColetasScreen(aoNovaColeta = { navController.navigate(Routes.DOADORA_NOVA_COLETA) })
            }
            composable(Routes.DOADORA_NOVA_COLETA) {
                DoadoraNovaColetaScreen(
                    aoConfirmar = {
                        navController.navigate(Routes.DOADORA_COLETAS) {
                            popUpTo(Routes.DOADORA_NOVA_COLETA) { inclusive = true }
                        }
                    },
                    aoVoltar = { navController.popBackStack() }
                )
            }
            composable(Routes.DOADORA_BANCOS) { DoadoraBancosScreen() }
            composable(Routes.DOADORA_CONTEUDO) {
                DoadoraConteudoListScreen(aoAbrirDetalhe = { slug -> navController.navigate(Routes.conteudoDetalhe(slug)) })
            }
            composable(
                route = Routes.DOADORA_CONTEUDO_DETALHE,
                arguments = listOf(navArgument("slug") { type = NavType.StringType })
            ) { backStack ->
                // Parâmetro "slug" recebido da tela de listagem de conteúdo, usado para
                // buscar o artigo correspondente nos dados mockados.
                val slug = backStack.arguments?.getString("slug") ?: ""
                DoadoraConteudoDetalheScreen(slug = slug, aoVoltar = { navController.popBackStack() })
            }
            composable(Routes.DOADORA_PERFIL) { DoadoraPerfilScreen() }
        }
    }
}