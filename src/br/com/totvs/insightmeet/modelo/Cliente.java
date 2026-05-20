package br.com.totvs.insightmeet.modelo;

public class Cliente {
    private int id;
    private String nome;
    private String segmento;
    private String emailContato;
    private int nivelSatisfacao;

    // Permite criar um cliente sem informar os dados no momento da instanciação.
    public Cliente() {
        this.nivelSatisfacao = 0;
    }

    // Sobrecarga de construtor com os dados principais do cliente.
    public Cliente(int id, String nome, String segmento, String emailContato) {
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.emailContato = emailContato;
        this.nivelSatisfacao = 0;
    }

    // Sobrecarga de construtor com nível de satisfação informado.
    public Cliente(int id, String nome, String segmento, String emailContato, int nivelSatisfacao) {
        this.id = id;
        this.nome = nome;
        this.segmento = segmento;
        this.emailContato = emailContato;
        setNivelSatisfacao(nivelSatisfacao);
    }

    public String exibirResumo() {
        return "Cliente: " + nome +
                " | Segmento: " + segmento +
                " | Email: " + emailContato +
                " | Satisfacao: " + nivelSatisfacao + "/5";
    }

    public boolean possuiBaixaSatisfacao() {
        return nivelSatisfacao >= 1 && nivelSatisfacao <= 2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public int getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    // Mantém o nível de satisfação dentro da escala permitida: 0 a 5.
    public void setNivelSatisfacao(int nivelSatisfacao) {
        if (nivelSatisfacao < 0) {
            this.nivelSatisfacao = 0;
        } else if (nivelSatisfacao > 5) {
            this.nivelSatisfacao = 5;
        } else {
            this.nivelSatisfacao = nivelSatisfacao;
        }
    }

}
