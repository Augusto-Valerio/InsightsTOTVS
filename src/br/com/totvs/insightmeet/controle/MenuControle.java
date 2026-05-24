package br.com.totvs.insightmeet.controle;

import br.com.totvs.insightmeet.dados.DadosSeed;
import br.com.totvs.insightmeet.modelo.*;
import br.com.totvs.insightmeet.servico.AnalisadorTranscricao;

import java.time.LocalDate;

public class MenuControle {
    private AnalisadorTranscricao analisadorTranscricao;
    private DadosSeed dadosSeed;
    private EntradaControle entradaControle;

    public MenuControle() {
        this.analisadorTranscricao = new AnalisadorTranscricao();
        this.dadosSeed = new DadosSeed();
        this.entradaControle = new EntradaControle();
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = entradaControle.lerOpcao();

            if (opcao == 1) {
                executarAnaliseComDadosSeed();
            } else if (opcao == 2) {
                executarAnaliseComEntradaUsuario();
            } else if (opcao == 0) {
                System.out.println("Encerrando o InsightMeet TOTVS...");
            } else {
                System.out.println("\nOpção inválida.");
            }

            System.out.println();
        }

        entradaControle.fechar();
    }

    private void exibirMenu() {
        System.out.println("==== InsightMeet TOTVS ====");
        System.out.println("1 - Analisar dados seed");
        System.out.println("2 - Digitar nova transcrição");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Executa a análise usando dados já cadastrados para teste.
    private void executarAnaliseComDadosSeed() {
        Cliente cliente = dadosSeed.criarClienteSeed();
        Reuniao reuniao = dadosSeed.criarReuniaoSeed(cliente);
        Transcricao transcricao = dadosSeed.criarTranscricaoSeed(reuniao);

        RelatorioAnalise relatorio = analisadorTranscricao.analisar(transcricao);

        exibirResultado(cliente, reuniao, transcricao, relatorio);
    }

    // Executa a análise com dados informados pelo usuário no console.
    private void executarAnaliseComEntradaUsuario() {
        String nomeCliente = entradaControle.lerTextoObrigatorio("Nome do cliente: ");

        String segmento = entradaControle.lerTextoObrigatorio("Segmento do cliente: ");

        String email = entradaControle.lerEmailObrigatorio("Email do cliente: ");

        int nivelDeSatisfacao = entradaControle.lerNivelSatisfacao();

        Cliente cliente = new Cliente(1, nomeCliente, segmento, email, nivelDeSatisfacao);

        String tituloReuniao = entradaControle.lerTextoObrigatorio("Título da reunião: ");

        String assunto = entradaControle.lerTextoObrigatorio("Assunto da reunião: ");

        Reuniao reuniao = new Reuniao(1, tituloReuniao, LocalDate.now(), assunto, cliente);

        cadastrarParticipantes(reuniao, cliente);

        String conteudo = entradaControle.lerTextoObrigatorio("Digite o texto da conversa da reunião: ");

        Transcricao transcricao = new Transcricao(
                1,
                conteudo,
                "pt-BR",
                reuniao
        );

        RelatorioAnalise relatorio = analisadorTranscricao.analisar(transcricao);

        exibirResultado(cliente, reuniao, transcricao, relatorio);
    }


    private void exibirParticipantes(Reuniao reuniao) {
        System.out.println("==== Anfitrião TOTVS ====");

        for (Participante participante : reuniao.getParticipantes()) {
            if (participante.pertenceEmpresa("TOTVS")) {
                System.out.println("Nome: " + participante.getNome() +
                        " | Cargo: " + participante.getCargo() +
                        " | Empresa: " + participante.getEmpresa() +
                        " | Email: " + participante.getEmail());
            }
        }

        System.out.println();
        System.out.println("==== Participantes do cliente ====");

        boolean encontrouParticipanteCliente = false;

        for (Participante participante : reuniao.getParticipantes()) {
            if (!participante.pertenceEmpresa("TOTVS")) {
                encontrouParticipanteCliente = true;

                System.out.println("- Nome: " + participante.getNome() +
                        " | Cargo: " + participante.getCargo() +
                        " | Empresa: " + participante.getEmpresa() +
                        " | Email: " + participante.getEmail());
            }
        }

        if (!encontrouParticipanteCliente) {
            System.out.println("Nenhum participante do cliente informado.");
        }
    }

    private void exibirResultado(Cliente cliente, Reuniao reuniao, Transcricao transcricao, RelatorioAnalise relatorio) {
        System.out.println();
        System.out.println("==== Resultado da Análise ====");

        System.out.println();
        System.out.println("==== Cliente ====");
        System.out.println(cliente.exibirResumo());

        System.out.println();
        System.out.println("==== Reunião ====");
        System.out.println(reuniao.exibirResumo());

        System.out.println();
        exibirParticipantes(reuniao);

        System.out.println();
        System.out.println("==== Transcrição ====");
        System.out.println(transcricao.exibirResumo());

        System.out.println();
        System.out.println("==== Relatório ====");
        System.out.println(relatorio.exibirResumo());
        System.out.println(relatorio.gerarResumoExecutivo());

        if (!entradaControle.aguardarEnterOuVoltar("Pressione Enter para ver os termos relevantes ou digite 0 para voltar ao menu.")) {
            return;
        }

        exibirTermosRelevantes(relatorio);

        if (!entradaControle.aguardarEnterOuVoltar("Pressione Enter para ver os insights gerados ou digite 0 para voltar ao menu.")) {
            return;
        }

        exibirInsights(relatorio);
    }

    private void exibirTermosRelevantes(RelatorioAnalise relatorio) {
        System.out.println();
        System.out.println("==== Termos relevantes: ====");
        if (relatorio.getTermosRelevantes().isEmpty()) {
            System.out.println("Nenhum termo relevante encontrado.");
        } else {
            for (TermoRelevante termo : relatorio.getTermosRelevantes()) {
                System.out.println("- " + termo.exibirResumo());
            }
        }
    }

    private void exibirInsights(RelatorioAnalise relatorio) {
        System.out.println();
        System.out.println("==== Insights gerados: ====");
        if (relatorio.getInsights().isEmpty()) {
            System.out.println("Nenhum insight gerado.");
        } else {

            for (Insight insight : relatorio.getInsights()) {
                System.out.println("\n- " + insight.exibirResumo());
                System.out.println(" Descrição: " + insight.getDescricao());
                System.out.println(" Ação recomendada: " + insight.gerarAcaoRecomendada());
            }
        }

        System.out.println();
        if (relatorio.possuiInsightsCriticos()) {
            System.out.println("==== Atenção: existem insights críticos para acompanhamento. ====");
        }
    }

    private void cadastrarParticipantes(Reuniao reuniao, Cliente cliente) {
        System.out.println("==== Anfitrião TOTVS ====");
        String nomeAnfitriao = entradaControle.lerTextoObrigatorio("Nome do anfitrião TOTVS: ");
        String cargoAnfitriao = entradaControle.lerTextoObrigatorio("Cargo do anfitrião TOTVS: ");
        String emailAnfitriao = entradaControle.lerEmailObrigatorio("Email do anfitrião TOTVS: ");

        Participante anfitriao = new Participante(
                1,
                nomeAnfitriao,
                cargoAnfitriao,
                "TOTVS",
                emailAnfitriao
        );

        reuniao.adicionarParticipante(anfitriao);

        int quantidadeParticipantes = entradaControle.lerQuantidadeParticipantes();

        for (int i = 1; i <= quantidadeParticipantes; i++) {
            System.out.println("==== Participante " + i + " do cliente ====");
            String nome = entradaControle.lerTextoObrigatorio("Nome: ");
            String cargo = entradaControle.lerTextoObrigatorio("Cargo: ");
            String email = entradaControle.lerEmailObrigatorio("Email: ");

            Participante participante = new Participante(
                    i + 1,
                    nome,
                    cargo,
                    cliente.getNome(),
                    email
            );

            reuniao.adicionarParticipante(participante);
        }
    }

}
