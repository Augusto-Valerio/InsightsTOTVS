package br.com.totvs.insightmeet.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatadorData {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatar(LocalDate data) {
        if (data == null) {
            return "Data não informada!";
        }

        return data.format(formatter);
    }
}
