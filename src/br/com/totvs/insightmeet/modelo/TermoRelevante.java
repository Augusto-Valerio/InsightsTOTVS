package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;

public class TermoRelevante implements Resumivel {

    private int id;
    private String termo;
    private int frequencia;
    private double relevancia;
    private Transcricao transcricao;

    // Permite criar um termo relevante sem informar os dados no momento da instanciação.
    public TermoRelevante() {
    }

    // Construtor com os dados principais do termo relevante.
    public TermoRelevante(int id, String termo, int frequencia, double relevancia, Transcricao transcricao) {
        this.id = id;
        this.termo = termo;
        setFrequencia(frequencia);
        setRelevancia(relevancia);
        this.transcricao = transcricao;
    }

    @Override
    public String exibirResumo() {
        return "Termo: " + termo +
                " | Frequencia: " + frequencia +
                " | Relevancia: " + relevancia;
    }

    public void aumentarFrequencia() {
        frequencia++;
    }

    public boolean possuiAltaRelevancia() {
        return relevancia >= 0.7;
    }

    public String classificarRelevancia() {
        if (relevancia >= 0.7) {
            return "Alta";
        } else if (relevancia >= 0.4) {
            return "Média";
        } else {
            return "Baixa";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        if (frequencia < 0) {
            this.frequencia = 0;
        } else {
            this.frequencia = frequencia;
        }
    }

    public double getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(double relevancia) {
        if (relevancia < 0) {
            this.relevancia = 0;
        } else if (relevancia > 1) {
            this.relevancia = 1;
        } else {
            this.relevancia = relevancia;
        }
    }

    public Transcricao getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(Transcricao transcricao) {
        this.transcricao = transcricao;
    }
}
