package br.com.totvs.insightmeet.dados;

import br.com.totvs.insightmeet.modelo.Cliente;
import br.com.totvs.insightmeet.modelo.Participante;
import br.com.totvs.insightmeet.modelo.Reuniao;
import br.com.totvs.insightmeet.modelo.Transcricao;

import java.time.LocalDate;

public class DadosSeed {
    // Centraliza dados iniciais para facilitar testes e demonstrações do sistema.
    public Cliente criarClienteSeed() {
        Cliente cliente = new Cliente(
                1,
                "TechStore LTDA",
                "Varejo",
                "contato@techstore.com",
                2
        );

        return cliente;
    }

    public Reuniao criarReuniaoSeed(Cliente cliente) {
        Reuniao reuniao = new Reuniao(
                1,
                "Alinhamento sobre atendimento e expansão",
                LocalDate.now(),
                "Experiencia do cliente",
                cliente
        );

        // Participantes usados para simular uma reunião real entre TOTVS e cliente.
        Participante participanteTotvs = new Participante(
                1,
                "Ana Souza",
                "Gerente de Relacionamento",
                "TOTVS",
                "ana.souza@totvs.com"
        );
        Participante participanteCliente = new Participante(
                2,
                "Carlos Mendes",
                "Coordenador de Operações",
                "TechStore LTDA",
                "carlos.mendes@techstore.com"
        );

        reuniao.adicionarParticipante(participanteTotvs);
        reuniao.adicionarParticipante(participanteCliente);

        return reuniao;
    }

    public Transcricao criarTranscricaoSeed(Reuniao reuniao) {
        // Texto com termos de risco e oportunidade para validar a análise automática.
        String conteudo = "O cliente relatou demora no suporte e demonstrou estar insatisfeito. " +
                "Apesar do problema, também mostrou interesse em expandir o contrato e contratar novos módulos.";

        Transcricao transcricao = new Transcricao(
                1,
                conteudo,
                "pt-BR",
                reuniao
        );

        return transcricao;
    }
}
