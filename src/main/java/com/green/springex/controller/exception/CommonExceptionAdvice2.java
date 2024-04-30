package com.green.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice  // 스프링 MC의 컨트롤러에서 발생하는 예외를 처리하는 가장 일반적인 방식, 컨트롤러에서 발생하는 예외에 맞게 처리할 수 있게 하는 기능 제공
@Log4j2
public class CommonExceptionAdvice2 {

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public String exceptNumber(NumberFormatException numberFormatException){
//        return "Number Format Exception";
//    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception){

        log.error("-----------------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>#Exception Happen# <br> " +exception.getMessage()+"  </li>");

//        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
//            buffer.append("<li>"+stackTraceElement+"</li>");
//        });
        buffer.append("</ul>");

        return buffer.toString();
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){

        return "custom404";
    }

}
