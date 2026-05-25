package br.com.totvs.insightmeet.modelo;

import br.com.totvs.insightmeet.interfaces.Resumivel;
import br.com.totvs.insightmeet.util.FormatadorData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reuniao implements Resumivel {
    private int id;
    private String titulo;
    private LocalDate data;
    private String assunto;
    private Cliente cliente;
    private List<Participante> participantes;

    // Permite criar uma reunião sem informar os dados no momento da instanciação.
    public Reuniao() {
        this.participantes = new ArrayList<>();
    }

    // Construtor com os dados principais da reunião.
    public Reuniao(int id, String titulo, LocalDate data, String assunto, Cliente cliente) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.assunto = assunto;
        this.cliente = cliente;
        this.participantes = new ArrayList<>();
    }

    // Sobrecarga de construtor permitindo informar uma lista inicial de participantes.
    public Reuniao(int id, String titulo, LocalDate data, String assunto, Cliente cliente, List<Participante> participantes) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.assunto = assunto;
        this.cliente = cliente;
        setParticipantes(participantes);
    }

    @Override
    public String exibirResumo() {
        return "Reunião: " + titulo +
                " | Data: " + FormatadorData.formatar(data) +
                " | Assunto: " + assunto +
                " | Participantes: " + participantes.size();
    }

    public void adicionarParticipante(Participante participante) {
        if (participante != null) {
            participantes.add(participante);
        }
    }

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        if (participantes == null) {
            this.participantes = new ArrayList<>();
        } else {
            this.participantes = participantes;
        }
    }

}
