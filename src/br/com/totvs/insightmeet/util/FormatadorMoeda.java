package br.com.totvs.insightmeet.util;

public class FormatadorMoeda {
    public static String formatter(double valor) {
        return "R$ " + String.format("%.2f", valor).replace('.', ',');
    }
}
