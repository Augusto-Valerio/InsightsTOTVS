package br.com.totvs.insightmeet.controle;

import br.com.totvs.insightmeet.dados.DadosSeed;
import br.com.totvs.insightmeet.modelo.*;
import br.com.totvs.insightmeet.servico.AnalisadorTranscricao;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuControle {
    private Scanner sc;
    private AnalisadorTranscricao analisadorTranscricao;
    private DadosSeed dadosSeed;

    public MenuControle() {
        this.sc = new Scanner(System.in);
        this.analisadorTranscricao = new AnalisadorTranscricao();
        this.dadosSeed = new DadosSeed();
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerOpcao();

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

        sc.close();
    }

    private void exibirMenu() {
        System.out.println("==== InsightMeet TOTVS ====");
        System.out.println("1 - Analisar dados seed");
        System.out.println("2 - Digitar nova transcrição");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        String entrada = sc.nextLine();

        if (entrada.equals("1")) {
            return 1;
        } else if (entrada.equals("2")) {
            return 2;
        } else if (entrada.equals("0")) {
            return 0;
        }
        return -1;
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
        String nomeCliente = lerTextoObrigatorio("Nome do cliente: ");

        String segmento = lerTextoObrigatorio("Segmento do cliente: ");

        String email = lerTextoObrigatorio("Email do cliente: ");

        int nivelDeSatisfacao = lerNivelSatisfacao();

        Cliente cliente = new Cliente(1, nomeCliente, segmento, email, nivelDeSatisfacao);

        String tituloReuniao = lerTextoObrigatorio("Título da reunião: ");

        String assunto = lerTextoObrigatorio("Assunto da reunião: ");

        Reuniao reuniao = new Reuniao(1, tituloReuniao, LocalDate.now(), assunto, cliente);

        String conteudo = lerTextoObrigatorio("Digite o texto da conversa da reunião: ");

        Transcricao transcricao = new Transcricao(
                1,
                conteudo,
                "pt-BR",
                reuniao
        );

        RelatorioAnalise relatorio = analisadorTranscricao.analisar(transcricao);

        exibirResultado(cliente, reuniao, transcricao, relatorio);
    }

    private void exibirResultado(Cliente cliente, Reuniao reuniao, Transcricao transcricao, RelatorioAnalise relatorio) {
        System.out.println();
        System.out.println("==== Resultado da Análise ====");
        System.out.println(cliente.exibirResumo());
        System.out.println(reuniao.exibirResumo());
        System.out.println(transcricao.exibirResumo());
        System.out.println(relatorio.exibirResumo());
        System.out.println(relatorio.gerarResumoExecutivo());

        System.out.println();
        System.out.println("==== Termos relevantes: ====");
        if (relatorio.getTermosRelevantes().isEmpty()) {
            System.out.println("Nenhum termo relevante encontrado.");
        } else {
            for (TermoRelevante termo : relatorio.getTermosRelevantes()) {
                System.out.println("- " + termo.exibirResumo());
            }
        }

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

    private String lerTextoObrigatorio(String mensagem) {
        String valor = "";

        while (valor.isBlank()) {
            System.out.print(mensagem);
            valor = sc.nextLine();

            if (valor.isBlank()) {
                System.out.println("Preencha esse campo.");
            }
        }

        return valor;
    }

    private int lerNivelSatisfacao() {
        String entrada = "";

        while (!entrada.equals("0") &&
                !entrada.equals("1") &&
                !entrada.equals("2") &&
                !entrada.equals("3") &&
                !entrada.equals("4") &&
                !entrada.equals("5")) {
            System.out.println("Nível de satisfação de 0 a 5: ");
            entrada = sc.nextLine();

            if (!entrada.equals("0") && !entrada.equals("1") &&
                    !entrada.equals("2") &&
                    !entrada.equals("3") &&
                    !entrada.equals("4") &&
                    !entrada.equals("5")) {
                System.out.println("Informe um número de 0 a 5.");
            }
        }
        return Integer.parseInt(entrada);
    }


}
