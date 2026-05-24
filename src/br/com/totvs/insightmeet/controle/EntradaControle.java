package br.com.totvs.insightmeet.controle;

import java.util.Scanner;

public class EntradaControle {

    private Scanner sc;

    public EntradaControle() {
        this.sc = new Scanner(System.in);
    }

    public int lerOpcao() {
        String entrada = sc.nextLine();

        if (entrada.equals("1")) {
            return 1;
        } else if (entrada.equals("2")) {
            return 2;
        } else if (entrada.equals("0")) {
            return 0;
        }

        return -1;
    }

    public String lerTextoObrigatorio(String mensagem) {
        String valor = "";

        while (valor.isBlank()) {
            System.out.print(mensagem);
            valor = sc.nextLine();

            if (valor.isBlank()) {
                System.out.println("Preencha esse campo.");
            }
        }

        return valor;
    }

    public String lerEmailObrigatorio(String mensagem) {
        String email = "";

        while (email.isBlank() || !email.contains("@")) {
            System.out.print(mensagem);
            email = sc.nextLine();

            if (email.isBlank()) {
                System.out.println("Preencha esse campo.");
            } else if (!email.contains("@")) {
                System.out.println("Email inválido. Tente novamente.");
            }
        }

        return email;
    }

    public int lerNivelSatisfacao() {
        String entrada = "";

        while (!entrada.equals("0") &&
                !entrada.equals("1") &&
                !entrada.equals("2") &&
                !entrada.equals("3") &&
                !entrada.equals("4") &&
                !entrada.equals("5")) {
            System.out.print("Nível de satisfação de 0 a 5: ");
            entrada = sc.nextLine();

            if (!entrada.equals("0") && !entrada.equals("1") &&
                    !entrada.equals("2") &&
                    !entrada.equals("3") &&
                    !entrada.equals("4") &&
                    !entrada.equals("5")) {
                System.out.println("Informe um número de 0 a 5.");
            }
        }
        return Integer.parseInt(entrada);
    }

    public int lerQuantidadeParticipantes() {
        String entrada = "";
        int quantidade = -1;

        while (quantidade < 0) {
            System.out.print("Quantos participantes do cliente estarão na reunião? ");
            entrada = sc.nextLine();

            if (entrada.isBlank()) {
                System.out.println("Preencha esse campo.");
            } else {
                try {
                    quantidade = Integer.parseInt(entrada);

                    if (quantidade < 0) {
                        System.out.println("Informe zero ou mais participantes.");
                    }
                } catch (NumberFormatException erro) {
                    System.out.println("Informe apenas números.");
                }
            }
        }

        return quantidade;
    }

    public void fechar() {
        sc.close();
    }
}
