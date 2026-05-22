package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAnalise implements Resumivel {

    private int id;
    private LocalDate dataGeracao;
    private Transcricao transcricao;
    private List<TermoRelevante> termosRelevantes;
    private List<Insight> insights;

    // Permite criar um relatório sem informar os dados no momento da instanciação.
    public RelatorioAnalise() {
        this.dataGeracao = LocalDate.now();
        this.termosRelevantes = new ArrayList<>();
        this.insights = new ArrayList<>();
    }

    // Construtor com os dados principais do relatório.
    public RelatorioAnalise(int id, Transcricao transcricao) {
        this.id = id;
        this.dataGeracao = LocalDate.now();
        this.transcricao = transcricao;
        this.termosRelevantes = new ArrayList<>();
        this.insights = new ArrayList<>();
    }

    @Override
    public String exibirResumo() {
        String tituloReuniao = transcricao != null && transcricao.getReuniao() != null
                ? transcricao.getReuniao().getTitulo()
                : "Reuniao nao informada";

        return "Relatorio da reuniao: " + tituloReuniao +
                " | Data: " + dataGeracao +
                " | Termos relevantes: " + termosRelevantes.size() +
                " | Insights: " + insights.size();
    }

    public void adicionarTermoRelevante(TermoRelevante termoRelevante) {
        if (termoRelevante != null) {
            termosRelevantes.add(termoRelevante);
        }
    }

    public void adicionarInsight(Insight insight) {
        if (insight != null) {
            insights.add(insight);
        }
    }

    public int contarInsightsDeRisco() {
        int total = 0;

        for (Insight insight : insights) {
            if (insight instanceof InsightRisco) {
                total++;
            }
        }

        return total;
    }

    public int contarInsightsDeOportunidade() {
        int total = 0;

        for (Insight insight : insights) {
            if (insight instanceof InsightOportunidade) {
                total++;
            }
        }

        return total;
    }

    public boolean possuiInsightsCriticos() {
        for (Insight insight : insights) {
            if (insight.possuiAltaPrioridade()) {
                return true;
            }
        }

        return false;
    }

    public String gerarResumoExecutivo() {
        return "Resumo executivo: " +
                contarInsightsDeRisco() + " risco(s), " +
                contarInsightsDeOportunidade() + " oportunidade(s), " +
                termosRelevantes.size() + " termos(s) relevante(s).";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Transcricao getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(Transcricao transcricao) {
        this.transcricao = transcricao;
    }

    public List<TermoRelevante> getTermosRelevantes() {
        return termosRelevantes;
    }

    public void setTermosRelevantes(List<TermoRelevante> termosRelevantes) {
        if (termosRelevantes == null) {
            this.termosRelevantes = new ArrayList<>();
        } else {
            this.termosRelevantes = termosRelevantes;
        }
    }

    public List<Insight> getInsights() {
        return insights;
    }

    public void setInsights(List<Insight> insights) {
        if (insights == null) {
            this.insights = new ArrayList<>();
        } else {
            this.insights = insights;
        }
    }
}
