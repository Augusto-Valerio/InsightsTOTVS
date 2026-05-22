package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;

import java.time.LocalDate;

public abstract class Insight implements Resumivel {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataGeracao;
    private int prioridade;
    private Transcricao transcricao;

    // Permite criar um insight sem informar os dados no momento da instanciação.
    public Insight() {
        this.dataGeracao = LocalDate.now();
        this.prioridade = 1;
    }

    // Construtor com os dados principais do insight.
    public Insight(int id, String titulo, String descricao, int prioridade, Transcricao transcricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataGeracao = LocalDate.now();
        setPrioridade(prioridade);
        this.transcricao = transcricao;
    }

    @Override
    public String exibirResumo() {
        return "Insight: " + titulo +
                " | Tipo: " + obterTipo() +
                " | Prioridade: " + prioridade +
                " | Data: " + dataGeracao;
    }

    public boolean possuiAltaPrioridade() {
        return prioridade >= 4;
    }

    public abstract String obterTipo();

    public abstract String gerarAcaoRecomendada();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        if (prioridade < 1) {
            this.prioridade = 1;
        } else if (prioridade > 5) {
            this.prioridade = 5;
        } else {
            this.prioridade = prioridade;
        }
    }

    public Transcricao getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(Transcricao transcricao) {
        this.transcricao = transcricao;
    }
}
