package br.com.totvs.insightmeet.servico;

import br.com.totvs.insightmeet.modelo.*;

public class AnalisadorTranscricao {

    // Palavras usadas para identificar possíveis riscos na transcrição.
    private final String[] termosRisco = {
            "cancelar", "cancelamento", "problema", "demora", "insatisfeito", "reclamacao", "reclamação"
    };

    // Palavras usadas para identificar possíveis oportunidades na transcrição.
    private final String[] termosOportunidade = {
            "interesse", "expandir", "contratar", "melhorar", "comprar"
    };

    // Gera um relatório com termos relevantes e insights encontrados na transcrição.
    public RelatorioAnalise analisar(Transcricao transcricao) {
        RelatorioAnalise relatorio = new RelatorioAnalise(1, transcricao);

        analisarTermosDeRisco(transcricao, relatorio);
        analisarTermosDeOportunidade(transcricao, relatorio);
        return relatorio;
    }

    private void analisarTermosDeRisco(Transcricao transcricao, RelatorioAnalise relatorio) {
        for (String termo : termosRisco) {
            int frequencia = contarOcorrencias(transcricao, termo);

            if (frequencia > 0) {
                double relevancia = calcularRelevancia(frequencia);

                TermoRelevante termoRelevante = new TermoRelevante(
                        relatorio.getTermosRelevantes().size() + 1,
                        termo,
                        frequencia,
                        relevancia,
                        transcricao
                );

                InsightRisco insight = new InsightRisco(
                        relatorio.getInsights().size() + 1,
                        "Risco identificado na experiência do cliente",
                        gerarDescricaoRisco(termo, frequencia),
                        calcularPrioridadeRisco(termo, frequencia),
                        transcricao,
                        calcularNivelRisco(termo, frequencia),
                        gerarCausaProvavelRisco(termo)
                );

                relatorio.adicionarTermoRelevante(termoRelevante);
                relatorio.adicionarInsight(insight);
            }
        }
    }

    private void analisarTermosDeOportunidade(Transcricao transcricao, RelatorioAnalise relatorio) {
        for (String termo : termosOportunidade) {
            int frequencia = contarOcorrencias(transcricao, termo);

            if (frequencia > 0) {
                double relevancia = calcularRelevancia(frequencia);

                TermoRelevante termoRelevante = new TermoRelevante(
                        relatorio.getTermosRelevantes().size() + 1,
                        termo,
                        frequencia,
                        relevancia,
                        transcricao
                );

                double potencialGanho = calcularPotencialGanho(termo);

                InsightOportunidade insight = new InsightOportunidade(
                        relatorio.getInsights().size() + 1,
                        "Oportunidade comercial identificada",
                        gerarDescricaoOportunidade(termo, frequencia),
                        calcularPrioridadeOportunidade(potencialGanho, frequencia),
                        transcricao,
                        definirAreaOportunidade(termo),
                        potencialGanho
                );

                relatorio.adicionarTermoRelevante(termoRelevante);
                relatorio.adicionarInsight(insight);
            }
        }
    }

    private int calcularPrioridadeRisco(String termo, int frequencia) {
        if ("cancelar".equalsIgnoreCase(termo) || "cancelamento".equalsIgnoreCase(termo)) {
            return 5;
        } else if (frequencia >= 3) {
            return 4;
        } else if (frequencia == 2) {
            return 3;
        } else {
            return 2;
        }
    }

    private String calcularNivelRisco(String termo, int frequencia) {
        if ("cancelar".equalsIgnoreCase(termo) || "cancelamento".equalsIgnoreCase(termo) || frequencia >= 3) {
            return "Alto";
        } else if (frequencia == 2) {
            return "Medio";
        } else {
            return "Baixo";
        }
    }

    private String gerarCausaProvavelRisco(String termo) {
        if ("cancelar".equalsIgnoreCase(termo) || "cancelamento".equalsIgnoreCase(termo)) {
            return "Cliente sinalizou possibilidade de encerramento da parceria.";
        } else if ("demora".equalsIgnoreCase(termo)) {
            return "Cliente percebe atraso ou lentidão no atendimento.";
        } else if ("problema".equalsIgnoreCase(termo)) {
            return "Cliente relatou falha ou dificuldade durante a experiência.";
        } else if ("insatisfeito".equalsIgnoreCase(termo)) {
            return "Cliente demonstrou insatisfação com o atendimento recebido.";
        } else {
            return "Cliente apresentou sinal de atenção durante a reunião.";
        }
    }

    private String gerarDescricaoRisco(String termo, int frequencia) {
        return "O termo '" + termo + "' apareceu " + frequencia +
                " vez(es), indicando possível ponto de atenção para o cliente.";
    }

    private double calcularPotencialGanho(String termo) {
        if ("expandir".equalsIgnoreCase(termo)) {
            return 12000.0;
        } else if ("contratar".equalsIgnoreCase(termo)) {
            return 10000.0;
        } else if ("comprar".equalsIgnoreCase(termo)) {
            return 7000.0;
        } else {
            return 3000.0;
        }
    }

    private int calcularPrioridadeOportunidade(double potencialGanho, int frequencia) {
        if (potencialGanho >= 10000) {
            return 5;
        } else if (potencialGanho >= 7000) {
            return 4;
        } else if (frequencia >= 3) {
            return 3;
        } else {
            return 2;
        }
    }

    private String definirAreaOportunidade(String termo) {
        if ("expandir".equalsIgnoreCase(termo)) {
            return "Expansão de contrato";
        } else if ("contratar".equalsIgnoreCase(termo) || "comprar".equalsIgnoreCase(termo)) {
            return "Nova venda";
        } else if ("melhorar".equalsIgnoreCase(termo)) {
            return "Melhoria de processo";
        } else {
            return "Relacionamento com cliente";
        }
    }

    private String gerarDescricaoOportunidade(String termo, int frequencia) {
        return "O termo '" + termo + "' apareceu " + frequencia +
                " vez(es), indicando possível oportunidade para evolução comercial.";
    }

    // Conta quantas vezes um termo aparece dentro do conteúdo da transcrição.
    private int contarOcorrencias(Transcricao transcricao, String termo) {
        if (transcricao == null || transcricao.getConteudo() == null || termo == null) {
            return 0;
        }

        String conteudo = transcricao.getConteudo().toLowerCase();
        String termoNormalizado = termo.toLowerCase();

        int contador = 0;
        int posicao = conteudo.indexOf(termoNormalizado);

        while (posicao >= 0) {
            contador++;
            posicao = conteudo.indexOf(termoNormalizado, posicao + termoNormalizado.length());
        }

        return contador;
    }

    // Converte a frequência encontrada em uma relevância entre 0 e 1.
    private double calcularRelevancia(int frequencia) {
        if (frequencia >= 3) {
            return 1;
        } else if (frequencia == 2) {
            return 0.7;
        } else if (frequencia == 1) {
            return 0.4;
        }

        return 0;
    }

}

