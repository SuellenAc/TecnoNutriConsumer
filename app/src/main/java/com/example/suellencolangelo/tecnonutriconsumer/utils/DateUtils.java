package com.example.suellencolangelo.tecnonutriconsumer.utils;

import java.text.SimpleDateFormat;

/**
 * Created by suellencolangelo on 01/03/17.
 */

public class DateUtils {
    public static final String DATE_ONLY_FORMAT = "dd/MM/yyyy";

    // Formata uma data para uma string
    public static String dateToString(java.util.Date date, String typeFormat) throws Exception {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(typeFormat);
            formatter.setLenient(false);
            return formatter.format(date);
        }
    }
}
