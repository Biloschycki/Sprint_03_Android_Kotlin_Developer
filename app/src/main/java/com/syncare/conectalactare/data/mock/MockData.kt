package com.syncare.conectalactare.data.mock

import com.syncare.conectalactare.data.model.*

object MockData {

    val perguntasQuiz = listOf(
        PerguntaQuiz(1, "Você está amamentando ou tirou leite nos últimos 12 meses?"),
        PerguntaQuiz(2, "Você está em bom estado geral de saúde?"),
        PerguntaQuiz(3, "Você não fuma nem usa outras substâncias?"),
        PerguntaQuiz(4, "Você não faz uso de medicação incompatível com a doação?"),
        PerguntaQuiz(5, "Você tem interesse em doar seu leite excedente regularmente?")
    )

    val bancos = listOf(
        BancoLeite(
            id = 1, nome = "Banco de Leite Humano — Hospital Pérola Byington",
            endereco = "Av. Brigadeiro Luís Antônio", numero = "683", bairro = "Bela Vista",
            cidade = "São Paulo", estado = "SP", telefone = "(11) 2176-1000",
            horarioFuncionamento = "Seg a Sex, 7h às 19h", distanciaKm = 2.4,
            modalidades = listOf(ModalidadeColeta.DOMICILIAR, ModalidadeColeta.PRESENCIAL)
        ),
        BancoLeite(
            id = 2, nome = "Banco de Leite — Hospital das Clínicas",
            endereco = "Av. Dr. Enéas de Carvalho Aguiar", numero = "255", bairro = "Cerqueira César",
            cidade = "São Paulo", estado = "SP", telefone = "(11) 2661-0000",
            horarioFuncionamento = "Seg a Sáb, 8h às 18h", distanciaKm = 4.1,
            modalidades = listOf(ModalidadeColeta.PRESENCIAL)
        ),
        BancoLeite(
            id = 3, nome = "Banco de Leite Humano — Maternidade Municipal",
            endereco = "Rua das Palmeiras", numero = "120", bairro = "Jardim América",
            cidade = "São Paulo", estado = "SP", telefone = "(11) 3345-2200",
            horarioFuncionamento = "Todos os dias, 24h", distanciaKm = 6.8,
            modalidades = listOf(ModalidadeColeta.DOMICILIAR, ModalidadeColeta.PRESENCIAL)
        )
    )

    val conteudos = listOf(
        Conteudo(
            id = 1, slug = "como-armazenar-leite", titulo = "Como armazenar seu leite corretamente",
            categoria = "COLETA", tempoLeituraMin = 3,
            resumo = "Passo a passo para congelar e guardar o leite excedente com segurança.",
            corpo = "Use potes de vidro esterilizados, identifique com data e horário da ordenha, " +
                    "e mantenha congelado a -3°C ou menos até a coleta. Evite reaproveitar embalagens " +
                    "de outros alimentos e nunca misture leite de ordenhas em temperaturas diferentes " +
                    "no mesmo pote sem seguir a orientação do banco de leite."
        ),
        Conteudo(
            id = 2, slug = "quem-pode-doar", titulo = "Quem pode doar leite materno?",
            categoria = "ELEGIBILIDADE", tempoLeituraMin = 2,
            resumo = "Entenda os critérios básicos de elegibilidade para se tornar doadora.",
            corpo = "Podem doar mulheres saudáveis que estejam amamentando e produzam leite " +
                    "excedente. Não é necessário estar com o bebê internado nem morar perto do banco " +
                    "de leite — o processo de triagem é feito pela equipe de saúde após o cadastro."
        ),
        Conteudo(
            id = 3, slug = "impacto-da-doacao", titulo = "O impacto de cada doação",
            categoria = "IMPACTO", tempoLeituraMin = 4,
            resumo = "Veja como 1 litro de leite doado pode alimentar até 10 bebês prematuros.",
            corpo = "O leite humano pasteurizado é o principal alimento de recém-nascidos " +
                    "prematuros internados em UTI neonatal. Cada doação passa por controle de " +
                    "qualidade rigoroso antes de chegar até os bebês que mais precisam."
        ),
        Conteudo(
            id = 4, slug = "cuidados-pos-parto", titulo = "Cuidados com a amamentação no pós-parto",
            categoria = "SAUDE", tempoLeituraMin = 5,
            resumo = "Dicas práticas para manter a produção de leite de forma saudável.",
            corpo = "Hidrate-se bem, mantenha uma alimentação equilibrada e procure ordenhar em " +
                    "horários regulares. Em caso de dor, vermelhidão ou febre, procure orientação " +
                    "médica antes de continuar a amamentação ou doação."
        )
    )

    var doadoraAtual: Doadora = Doadora(
        id = 1,
        nome = "Mariana Silva Santos",
        email = "mariana.silva@email.com",
        cpf = "12345678900",
        telefone = "11987654321",
        dataNascimento = "1994-03-12",
        pesoKg = 62.5,
        cep = "01310100",
        endereco = "Av. Paulista",
        numero = "1000",
        complemento = "Apto 52",
        bairro = "Bela Vista",
        cidade = "São Paulo",
        estado = "SP",
        statusAvaliacao = StatusAvaliacao.APROVADA,
        diaColetaSemana = 3, // quarta-feira
        doadoraDesde = "2025-11-02"
    )

    val coletas = mutableListOf(
        Coleta(
            id = 1, doadoraId = 1, banco = bancos[0], dataAgendada = "2026-08-19T09:00:00",
            modalidade = ModalidadeColeta.DOMICILIAR, status = StatusColeta.AGENDADA
        ),
        Coleta(
            id = 2, doadoraId = 1, banco = bancos[0], dataAgendada = "2026-08-05T09:00:00",
            modalidade = ModalidadeColeta.DOMICILIAR, status = StatusColeta.CONCLUIDA, volumeMl = 850
        ),
        Coleta(
            id = 3, doadoraId = 1, banco = bancos[1], dataAgendada = "2026-07-22T14:00:00",
            modalidade = ModalidadeColeta.PRESENCIAL, status = StatusColeta.CONCLUIDA, volumeMl = 620
        ),
        Coleta(
            id = 4, doadoraId = 1, banco = bancos[0], dataAgendada = "2026-07-08T09:00:00",
            modalidade = ModalidadeColeta.DOMICILIAR, status = StatusColeta.CONCLUIDA, volumeMl = 700
        )
    )

    val outrasDoadoras = listOf(
        Doadora(2, "Camila Rocha Oliveira", "camila.rocha@email.com", "22233344455", "11911112222",
            "1991-07-20", 58.0, "04567000", "Rua Vergueiro", "500", "", "Vila Mariana", "São Paulo", "SP",
            StatusAvaliacao.PENDENTE, null, "2026-08-10"),
        Doadora(3, "Juliana Mendes Costa", "juliana.mendes@email.com", "33344455566", "11922223333",
            "1996-01-15", 65.2, "05678000", "Rua Augusta", "220", "", "Consolação", "São Paulo", "SP",
            StatusAvaliacao.APROVADA, 5, "2025-09-18"),
        Doadora(4, "Beatriz Almeida Souza", "beatriz.almeida@email.com", "44455566677", "11933334444",
            "1993-11-02", 60.0, "03456000", "Rua Cardeal Arcoverde", "80", "", "Pinheiros", "São Paulo", "SP",
            StatusAvaliacao.PENDENTE, null, "2026-08-14"),
        Doadora(5, "Fernanda Lima Costa", "fernanda.lima@email.com", "55566677788", "11944445555",
            "1990-05-30", 70.1, "02345000", "Rua Cotoxó", "12", "", "Perdizes", "São Paulo", "SP",
            StatusAvaliacao.REPROVADA, null, "2026-06-01"),
    )

    val filaAvaliacao = listOf(
        ItemAvaliacao(1, outrasDoadoras[0], "2026-08-14"),
        ItemAvaliacao(2, outrasDoadoras[2], "2026-08-15")
    )

    val auditoria = listOf(
        LogAuditoria(1, "Dra. Renata Alves", "Aprovou cadastro", "Juliana Mendes Costa", "2026-08-12 10:22"),
        LogAuditoria(2, "Enf. Paulo Torres", "Reprovou cadastro", "Fernanda Lima Costa", "2026-06-02 15:40"),
        LogAuditoria(3, "Dra. Renata Alves", "Editou conteúdo", "Como armazenar seu leite corretamente", "2026-08-05 09:15"),
        LogAuditoria(4, "Enf. Paulo Torres", "Confirmou coleta", "Mariana Silva Santos", "2026-08-05 09:05")
    )

    fun todasDoadoras(): List<Doadora> = listOf(doadoraAtual) + outrasDoadoras

    fun totalLitrosPlataforma(): Double =
        coletas.filter { it.status == StatusColeta.CONCLUIDA }.sumOf { it.volumeMl ?: 0 } / 1000.0
}