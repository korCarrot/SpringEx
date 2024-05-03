package com.green.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

//Formatter 구현클래스는 컨트롤러에서 실행된다. 컨트롤러의 처리과정에서 요청된 데이터를 원하는 형식으로 변환하거나 포맷팅한다.
public class CheckboxFormatter implements Formatter<Boolean> {
    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null ) {
            return false;
        }
//      return text.equals("on");은 입력된 문자열이 "on"인지 여부를 확인하여 그 결과를 반환하는 구문
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
