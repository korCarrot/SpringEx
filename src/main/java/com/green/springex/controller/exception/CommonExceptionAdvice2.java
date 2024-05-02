package com.green.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice  // 스프링 MC의 컨트롤러에서 발생하는 예외를 처리하는 가장 일반적인 방식, 컨트롤러에서 발생하는 예외에 맞게 처리할 수 있게 하는 기능 제공
@Log4j2
public class CommonExceptionAdvice2 {

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public String exceptNumber(NumberFormatException numberFormatException){
//        return "Number Format Exception";
//    }

//    Exception.class를 사용해버리면 다른 모든 에러도 여기서 처리가 되어버림.

//    exception.getStackTrace()는 현재 예외의 스택 트레이스를 가져옵니다. 이 메서드는 StackTraceElement 객체의 배열을 반환합니다. 각 StackTraceElement 객체는 호출 스택의 한 요소를 나타냅니다.
//    Arrays.stream(exception.getStackTrace())은 StackTraceElement 배열을 스트림으로 변환합니다. 이렇게 하면 각 스택 트레이스 요소를 개별적으로 처리할 수 있습니다.
//
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception){

        log.error("-----------------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>#Exception Happen# <br> " +exception.getMessage()+"  </li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }



    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException){
        log.info(numberFormatException.getMessage());
        return "NumberFormatException";
    }
}
