package com.syncare.conectalactare.data.model

enum class StatusAvaliacao(val label: String) {
    PENDENTE("Em avaliação"),
    APROVADA("Aprovada"),
    REPROVADA("Não aprovada")
}

enum class StatusColeta(val label: String) {
    AGENDADA("Agendada"),
    EM_COLETA("Em coleta"),
    CONCLUIDA("Concluída"),
    PROBLEMA("Problema"),
    CANCELADA("Cancelada")
}

enum class ModalidadeColeta(val label: String) {
    DOMICILIAR("Coleta em casa"),
    PRESENCIAL("Levar ao banco")
}

data class Doadora(
    val id: Long,
    val nome: String,
    val email: String,
    val cpf: String,
    val telefone: String,
    val dataNascimento: String,
    val pesoKg: Double,
    val cep: String,
    val endereco: String,
    val numero: String,
    val complemento: String = "",
    val bairro: String,
    val cidade: String,
    val estado: String,
    var statusAvaliacao: StatusAvaliacao,
    var diaColetaSemana: Int? = null, // 0=domingo ... 6=sábado
    val doadoraDesde: String
)

data class BancoLeite(
    val id: Long,
    val nome: String,
    val endereco: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val telefone: String,
    val horarioFuncionamento: String,
    val distanciaKm: Double,
    val modalidades: List<ModalidadeColeta>
)

data class Coleta(
    val id: Long,
    val doadoraId: Long,
    val banco: BancoLeite,
    val dataAgendada: String,
    val modalidade: ModalidadeColeta,
    var status: StatusColeta,
    val volumeMl: Int? = null
)

data class Conteudo(
    val id: Long,
    val slug: String,
    val titulo: String,
    val categoria: String,
    val tempoLeituraMin: Int,
    val resumo: String,
    val corpo: String
)

data class PerguntaQuiz(
    val id: Long,
    val texto: String
)

data class ItemAvaliacao(
    val id: Long,
    val doadora: Doadora,
    val dataSolicitacao: String
)

data class LogAuditoria(
    val id: Long,
    val autor: String,
    val acao: String,
    val alvo: String,
    val dataHora: String
)

enum class PapelUsuario(val label: String) {
    DOADORA("Doadora"),
    GESTOR("Gestor")
}