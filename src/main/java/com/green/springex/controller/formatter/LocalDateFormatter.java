package com.green.springex.controller.formatter;




// 문자열을 객체로, 객체를 문자열로 변환, 문자열을 포맷을 이용해서 특정한 객체로 변환하는 경우에 사용
import org.springframework.format.Formatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    //    parse 메서드는 문자열을 LocalDate 객체로 변환합니다. 이 메서드는 주어진 텍스트를 "yyyy-MM-dd" 형식의 날짜로 파싱하여 LocalDate 객체로 반환
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    //    print 메서드는 LocalDate 객체를 문자열로 변환합니다. 이 메서드는 주어진 LocalDate 객체를 "yyyy-MM-dd" 형식의 문자열로 포맷팅하여 반환
    @Override
    public String print(LocalDate object, Locale locale) {
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
    }
    //    DateTimeFormatter.ofPattern("yyyy-MM-dd")를 사용하여 "yyyy-MM-dd" 형식의 날짜와 시간 포맷터를 생성하고,
//    이를 사용하여 LocalDate 객체를 문자열로 포맷. DateTimeFormatter 클래스는 날짜 및 시간 형식을 지정하고 파싱하기 위한 클래스
//    ofPattern 메서드는 주어진 패턴을 사용하여 DateTimeFormatter를 생성합니다. "yyyy-MM-dd" 패턴은 연도(네 자리), 월(두 자리), 일(두 자리)로 이루어진 ISO 형식의 날짜
//    format 메서드는 DateTimeFormatter 클래스에 속한 메서드로, 날짜와 시간을 특정 형식의 문자열로 변환

//    Locale 매개변수는 인터페이스의 일관성을 유지하고 향후 다국어 지원을 고려하여 포함되었지만, 이 메서드에서는 사용되지 않습니다.

}
