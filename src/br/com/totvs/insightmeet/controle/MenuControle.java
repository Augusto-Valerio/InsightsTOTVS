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

        String email = lerEmailObrigatorio("Email do cliente: ");

        int nivelDeSatisfacao = lerNivelSatisfacao();

        Cliente cliente = new Cliente(1, nomeCliente, segmento, email, nivelDeSatisfacao);

        String tituloReuniao = lerTextoObrigatorio("Título da reunião: ");

        String assunto = lerTextoObrigatorio("Assunto da reunião: ");

        Reuniao reuniao = new Reuniao(1, tituloReuniao, LocalDate.now(), assunto, cliente);

        cadastrarParticipantes(reuniao, cliente);

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

    private void cadastrarParticipantes(Reuniao reuniao, Cliente cliente) {
        System.out.println("==== Anfitrião TOTVS ====");
        String nomeAnfitriao = lerTextoObrigatorio("Nome do anfitrião TOTVS: ");
        String cargoAnfitriao = lerTextoObrigatorio("Cargo do anfitrião TOTVS: ");
        String emailAnfitriao = lerEmailObrigatorio("Email do anfitrião TOTVS: ");

        Participante anfitriao = new Participante(
                1,
                nomeAnfitriao,
                cargoAnfitriao,
                "TOTVS",
                emailAnfitriao
        );

        reuniao.adicionarParticipante(anfitriao);

        int quantidadeParticipantes = lerQuantidadeParticipantes();

        for (int i = 1; i <= quantidadeParticipantes; i++) {
            System.out.println("==== Participante " + i + " do cliente ====");
            String nome = lerTextoObrigatorio("Nome: ");
            String cargo = lerTextoObrigatorio("Cargo: ");
            String email = lerEmailObrigatorio("Email: ");

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

    private int lerQuantidadeParticipantes() {
        String entrada = "";
        int quantidade = -1;

        while (quantidade < 0) {
            System.out.print("Quantos participantes do cliente estarão na reunião? ");
            entrada = sc.nextLine();

            if (entrada.isBlank()) {
                System.out.println("Preencha esse campo.");
            } else {
                try {
                    quantidade = Integer.parseInt(entrada);

                    if (quantidade < 0) {
                        System.out.println("Informe zero ou mais participantes.");
                    }
                } catch (NumberFormatException erro) {
                    System.out.println("Informe apenas números.");
                }
            }
        }

        return quantidade;
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
            System.out.print("Nível de satisfação de 0 a 5: ");
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

    private String lerEmailObrigatorio(String mensagem) {
        String email = "";

        while (email.isBlank() || !email.contains("@")) {
            System.out.print(mensagem);
            email = sc.nextLine();

            if (email.isBlank()) {
                System.out.println("Preencha esse campo.");
            } else if (!email.contains("@")) {
                System.out.println("Email inválido. Tente novamente.");
            }
        }

        return email;
    }


}
