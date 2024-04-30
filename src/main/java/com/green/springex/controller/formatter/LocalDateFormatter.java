package com.green.springex.controller.formatter;




// 문자열을 객체로, 객체를 문자열로 변환, 문자열을 포맷을 이용해서 특정한 객체로 변환하는 경우에 사용
import org.springframework.format.Formatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
}
