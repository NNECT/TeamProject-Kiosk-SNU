package com.KioskSNU.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private static final String datePattern = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter formatter;

    public LocalDateTimeFormatter() {
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return formatter.format(object);
    }
}
