package br.com.totvs.insightmeet.modelo;

public class InsightRisco extends Insight {

    private String nivelRisco;
    private String causaProvavel;

    public InsightRisco() {
        super();
        this.nivelRisco = "Baixo";
    }

    public InsightRisco(int id, String titulo, String descricao, int prioridade, Transcricao transcricao, String nivelRisco, String causaProvavel) {
        super(id, titulo, descricao, prioridade, transcricao);
        this.nivelRisco = nivelRisco;
        this.causaProvavel = causaProvavel;
    }

    @Override
    public String obterTipo() {
        return "Risco";
    }

    @Override
    public String gerarAcaoRecomendada() {
        if ("Alto".equalsIgnoreCase(nivelRisco)) {
            return "Acionar imediatamente a equipe de relacionamento com o cliente.";
        } else if ("Medio".equalsIgnoreCase(nivelRisco) || "Médio".equalsIgnoreCase(nivelRisco)) {
            return "Agendar acompanhamento com o cliente e monitorar a situação.";
        } else {
            return "Registrar o risco e acompanhar nas próximas reuniões";
        }
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() +
                " | Nivel de risco: " + nivelRisco +
                " | Causa provável: " + causaProvavel;
    }

    public boolean exigeAcaoImediata() {
        return "Alto".equalsIgnoreCase(nivelRisco) || possuiAltaPrioridade();
    }

    public String getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public String getCausaProvavel() {
        return causaProvavel;
    }

    public void setCausaProvavel(String causaProvavel) {
        this.causaProvavel = causaProvavel;
    }
}
