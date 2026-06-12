<h1 align="center">InsightsTOTVS</h1>

<p align="center">
  <a href="#projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#integrantes">Integrantes</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#funcionalidades">Funcionalidades</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#conceitos-aplicados">Conceitos Aplicados</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#estrutura-do-projeto">Estrutura</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#diagrama-uml">Diagrama UML</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#database">Database</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#documentação">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#como-executar">Como Executar</a>
</p>

---

## Projeto

O **InsightsTOTVS** é uma aplicação desenvolvida em **Java puro** para analisar transcrições de reuniões entre a TOTVS e seus clientes.

A solução permite cadastrar dados do cliente, reunião, participantes e transcrição da conversa. A partir do texto informado, o sistema identifica termos relevantes, riscos e oportunidades comerciais, gerando um relatório com insights, prioridade, causa provável, potencial de ganho e ação recomendada.

O projeto foi desenvolvido sem Spring Boot ou frameworks externos, com foco nos conceitos de **Domain Driven Design**, **orientação a objetos** e organização em pacotes.

---

## Integrantes

| Nome | RM |
|---|---|
| [Augusto Valerio](https://github.com/Augusto-Valerio) | 562185 |
| [Jonas Esteves França](https://github.com/JonasEstevess) | 564143 |
| [Mariana Silva Oliveira](https://github.com/Marirsil) | 564241 |
| [Pedro Marchese](https://github.com/PedroMarchese01) | 563339 |
| [Vitor Rodrigues Tigre](https://github.com/VitorTigre) | 561746 |

---

## Funcionalidades

<details>
<summary>Ver funcionalidades</summary>

- Cadastro de cliente.
- Cadastro de reunião.
- Cadastro de anfitrião da TOTVS.
- Cadastro de participantes do cliente.
- Registro da transcrição da reunião.
- Análise automática da transcrição.
- Identificação de termos relevantes.
- Geração de insights de risco.
- Geração de insights de oportunidade.
- Cálculo de relevância dos termos.
- Definição de prioridade dos insights.
- Exibição de relatório final da análise.
- Uso de dados seed para testes e demonstração.

</details>

---

## Conceitos Aplicados

<details>
<summary>Ver conceitos de Java</summary>

- Classes e objetos.
- Encapsulamento.
- Construtores.
- Sobrecarga de construtores.
- Getters e setters.
- Herança.
- Classe abstrata: `Insight`.
- Polimorfismo com `InsightRisco` e `InsightOportunidade`.
- Sobrescrita de métodos.
- Atributos de referência entre classes.
- Listas com `ArrayList` e `List`.
- Entrada de dados com `Scanner`.
- Organização em pacotes.
- Métodos utilitários para formatação de data e moeda.

</details>

---

## Estrutura do Projeto

<details>
<summary>Ver estrutura</summary>

```txt
src
└── br
    └── com
        └── totvs
            └── insightmeet
                ├── controle
                │   ├── EntradaControle.java
                │   └── MenuControle.java
                │
                ├── dados
                │   └── DadosSeed.java
                │
                ├── modelo
                │   ├── Cliente.java
                │   ├── Participante.java
                │   ├── Reuniao.java
                │   ├── Transcricao.java
                │   ├── TermoRelevante.java
                │   ├── Insight.java
                │   ├── InsightRisco.java
                │   ├── InsightOportunidade.java
                │   └── RelatorioAnalise.java
                │
                ├── servico
                │   └── AnalisadorTranscricao.java
                │
                ├── util
                │   ├── FormatadorData.java
                │   └── FormatadorMoeda.java
                │
                └── Main.java
```

</details>

---

## Diagrama UML

O diagrama UML apresenta as principais classes de modelo do sistema, seus atributos, métodos e relacionamentos.

Ele contempla entidades como `Cliente`, `Reuniao`, `Participante`, `Transcricao`, `TermoRelevante`, `Insight`, `InsightRisco`, `InsightOportunidade` e `RelatorioAnalise`.

[Diagrama UML - InsightsTOTVS](https://miro.com/app/board/uXjVHOtJRkw=/?focusWidget=3458764671247434225)

---

## Database

A modelagem de banco de dados foi desenvolvida utilizando o **Oracle SQL Developer Data Modeler**.

Mesmo que a aplicação Java atual execute em memória pelo console, a modelagem database representa como os dados do sistema poderiam ser estruturados em uma base relacional.

| Material | Link |
|---|---|
| Modelo Database | [Acessar modelo database](https://drive.google.com/file/d/119oLNvxDMVxR1zTVKhv_q01NqWle8RWc/view) |
| Documentação Database | [Acessar documentação database](https://www.canva.com/design/DAHKovymkKY/4sY_t3e5JpVntOPy3f5jbg/edit) |

---

## Documentação

A documentação do projeto contém capa, sumário, descritivo da solução e modelagem UML.

[Documentação Java - InsightsTOTVS](https://drive.google.com/file/d/1y0vXsqeg4Khi5zRmuouuB5D2msLHollS/view?usp=sharing)

---

## Como Executar

<details>
<summary>Executar pelo IntelliJ IDEA</summary>

1. Abra o projeto no IntelliJ IDEA.
2. Verifique se o SDK do Java está configurado.
3. Acesse o arquivo `Main.java`.
4. Execute o método `main`.
5. Use o menu exibido no console.

</details>

<details>
<summary>Executar pelo terminal PowerShell</summary>

Compile o projeto:

```bash
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java -Path src).FullName
```

Execute:

```bash
java -cp out br.com.totvs.insightmeet.Main
```

</details>

---