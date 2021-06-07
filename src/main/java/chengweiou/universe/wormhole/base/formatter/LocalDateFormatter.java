package chengweiou.universe.wormhole.base.formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        try {
            return LocalDate.parse(text);
        } catch(DateTimeParseException ex) {
            return LocalDateTime.parse(text).toLocalDate();
        }
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.toString();
    }
}
