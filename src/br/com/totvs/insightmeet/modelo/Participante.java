package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;

public class Participante implements Resumivel {
    private int id;
    private String nome;
    private String cargo;
    private String empresa;
    private String email;

    // Permite criar um participante sem informar os dados no momento da instanciação.
    public Participante() {
    }

    // Construtor com os dados principais do participante.
    public Participante(int id, String nome, String cargo, String empresa, String email) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.empresa = empresa;
        this.email = email;
    }

    @Override
    public String exibirResumo() {
        return "Participante: " + nome +
                " | Cargo: " + cargo +
                " | Empresa: " + empresa +
                " | Email: " + email;
    }

    public boolean pertenceEmpresa(String nomeEmpresa) {
        return empresa != null && empresa.equalsIgnoreCase(nomeEmpresa);
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
