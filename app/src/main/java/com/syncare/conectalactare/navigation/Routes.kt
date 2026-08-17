package com.syncare.conectalactare.navigation

object Routes {
    const val LANDING = "landing"
    const val QUIZ = "quiz"
    const val RESULTADO_ELEGIVEL = "resultado_elegivel"
    const val RESULTADO_INELEGIVEL = "resultado_inelegivel"
    const val CADASTRO = "cadastro"
    const val LOGIN = "login"

    // Área da doadora
    const val DOADORA_INICIO = "doadora_inicio"
    const val DOADORA_COLETAS = "doadora_coletas"
    const val DOADORA_NOVA_COLETA = "doadora_nova_coleta"
    const val DOADORA_BANCOS = "doadora_bancos"
    const val DOADORA_CONTEUDO = "doadora_conteudo"
    const val DOADORA_CONTEUDO_DETALHE = "doadora_conteudo_detalhe/{slug}"
    const val DOADORA_PERFIL = "doadora_perfil"

    // Área do gestor
    const val GESTOR_VISAO_GERAL = "gestor_visao_geral"
    const val GESTOR_AVALIACOES = "gestor_avaliacoes"
    const val GESTOR_DOADORAS = "gestor_doadoras"
    const val GESTOR_COLETAS = "gestor_coletas"
    const val GESTOR_CONTEUDO = "gestor_conteudo"
    const val GESTOR_AUDITORIA = "gestor_auditoria"

    fun conteudoDetalhe(slug: String) = "doadora_conteudo_detalhe/$slug"
}