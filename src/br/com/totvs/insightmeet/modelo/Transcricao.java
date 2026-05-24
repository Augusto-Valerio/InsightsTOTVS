package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;

public class Transcricao implements Resumivel {

    private int id;
    private String conteudo;
    private String idioma;
    private int quantidadePalavras;
    private Reuniao reuniao;

    // Permite criar uma transcrição sem informar os dados no momento da instanciação.
    public Transcricao() {
        this.idioma = "pt-BR";
    }

    // Construtor com os dados principais da transcrição.
    public Transcricao(int id, String conteudo, String idioma, Reuniao reuniao) {
        this.id = id;
        setConteudo(conteudo);
        this.idioma = idioma;
        this.reuniao = reuniao;
    }

    @Override
    public String exibirResumo() {
        String tituloReuniao = reuniao != null ? reuniao.getTitulo() : "Reuniao não informada";

        return "Transcrição da reunião: " + tituloReuniao +
                " | Idioma: " + idioma +
                " | Palavras: " + quantidadePalavras;
    }

    public void atualizarQuantidadePalavras() {
        if (conteudo == null || conteudo.isBlank()) {
            quantidadePalavras = 0;
        } else {
            quantidadePalavras = conteudo.trim().split("\\s+").length;
        }
    }

    public boolean contemTermo(String termo) {
        if (conteudo == null || termo == null) {
            return false;
        }
        return conteudo.toLowerCase().contains(termo.toLowerCase());
    }

    public String gerarTrechoInicial() {
        if (conteudo == null || conteudo.isBlank()) {
            return "Transcrição sem conteúdo.";
        }

        if (conteudo.length() < 80) {
            return conteudo;
        }

        return conteudo.substring(0, 80) + "...";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
        atualizarQuantidadePalavras();
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getQuantidadePalavras() {
        return quantidadePalavras;
    }

    public void setQuantidadePalavras(int quantidadePalavras) {
        this.quantidadePalavras = quantidadePalavras;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }
}
