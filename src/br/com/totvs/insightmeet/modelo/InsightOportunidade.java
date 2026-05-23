package br.com.totvs.insightmeet.modelo;

public class InsightOportunidade extends Insight {
    private String areaOportunidade;
    private double potencialGanho;

    // Permite criar um insight de oportunidade sem informar os dados no momento da instanciação.
    public InsightOportunidade() {
        super();
        this.potencialGanho = 0;
    }

    // Construtor com os dados principais do insight de oportunidade.
    public InsightOportunidade(int id, String titulo, String descricao, int prioridade,
                               Transcricao transcricao, String areaOportunidade, double potencialGanho) {
        super(id, titulo, descricao, prioridade, transcricao);
        this.areaOportunidade = areaOportunidade;
        setPotencialGanho(potencialGanho);
    }

    @Override
    public String obterTipo() {
        return "Oportunidade";
    }

    @Override
    public String gerarAcaoRecomendada() {
        if (potencialGanho >= 10000) {
            return "Encaminhar oportunidade para o time comercial com prioridade alta.";
        } else if (potencialGanho >= 3000) {
            return "Avaliar proposta comercial e acompanhar interesse do cliente.";
        } else {
            return "Registrar oportunidade para acompanhamento futuro.";
        }
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() +
                " | Area da oportunidade: " + areaOportunidade +
                " | Potencial de ganho: R$ " + potencialGanho;
    }

    public boolean possuiAltoPotencial() {
        return potencialGanho >= 10000;
    }

    public String getAreaOportunidade() {
        return areaOportunidade;
    }

    public void setAreaOportunidade(String areaOportunidade) {
        this.areaOportunidade = areaOportunidade;
    }

    public double getPotencialGanho() {
        return potencialGanho;
    }

    public void setPotencialGanho(double potencialGanho) {
        if (potencialGanho < 0) {
            this.potencialGanho = 0;
        } else {
            this.potencialGanho = potencialGanho;
        }
    }
}
