package chengweiou.universe.wormhole.base.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        try {
            return LocalDateTime.parse(text);
        } catch(DateTimeParseException ex) {
            return LocalDate.parse(text).atStartOfDay();
        }
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.toString();
    }
}
