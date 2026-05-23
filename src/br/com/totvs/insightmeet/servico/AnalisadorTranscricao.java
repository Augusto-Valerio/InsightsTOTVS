package br.com.totvs.insightmeet.servico;

import br.com.totvs.insightmeet.modelo.*;

public class AnalisadorTranscricao {

    // Palavras usadas para identificar possíveis riscos e oportunidades na transcrição.
    private final String[] termosRisco = {
            "cancelar", "cancelamento", "problema", "demora", "insatisfeito", "reclamacao", "reclamação"
    };

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
                        "Risco identificado: " + termo,
                        "A transcrição apresentou termo associado a risco para o cliente.",
                        4,
                        transcricao,
                        "Alto",
                        "termo sensível encontrado: " + termo
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

                InsightOportunidade insight = new InsightOportunidade(
                        relatorio.getInsights().size() + 1,
                        "Oportunidade identificada: " + termo,
                        "A transcrição apresentou o termo associado a oportunidade comercial.",
                        3,
                        transcricao,
                        "Relacionamento com cliente",
                        5000.0
                );

                relatorio.adicionarTermoRelevante(termoRelevante);
                relatorio.adicionarInsight(insight);
            }
        }
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

