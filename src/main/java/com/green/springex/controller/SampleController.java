package com.green.springex.controller;

import com.green.springex.dto.TodoDTO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
public class SampleController {

    //return type이 void인 경우 hi 요청 후 hi.jsp
    @GetMapping("/hi")
    public void hello(){
        log.info("하이로 요청함#" + "hello");
    }

//    @GetMapping("/hi3")
//    public TodoDTO hello3(){
//        log.info("하이3로 요청함##" + "hello");
//        return new TodoDTO(1L,"제목1", null, false );
//    }

//    @GetMapping("/hi3")
//    public Map<String, TodoDTO> hello3(){
//        log.info("하이3로 요청함##" + "hello");
//        TodoDTO todoDTO=new TodoDTO(1L,"제목1", null, false );
//        Map<String, TodoDTO> map=new HashMap<String, TodoDTO>();
//        map.put("todoDTO", todoDTO);
//        return map;
//
//    }

    //return type이 String인 경우 hi2 요청 후 aaa.jsp
    @GetMapping("/hi2")
    public String hello2(){
        log.info("hello2");
        return "aaa";
    }


    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("ex4");
        model.addAttribute("message", "nice");
    }

    @GetMapping("/todoModel")
    public void todoModel(TodoDTO todoDTO, Model model){
        todoDTO.setTno(6L);
        todoDTO.setTitle("글제목5");
       // todoDTO.setDueDate(LocalDate.parse("2024-4-20"));
        todoDTO.setDueDate(LocalDate.parse("2024-04-20"));
        todoDTO.setFinished(false);
        log.info(todoDTO);
        model.addAttribute("todoDTO3", todoDTO);
    }
    @GetMapping("/todoModelAttribute")
    public void todoModelAttribute(@ModelAttribute("dto")TodoDTO todoDTO){
        todoDTO.setTno(7L);
        todoDTO.setTitle("글제목7");
        todoDTO.setDueDate(LocalDate.parse("2024-04-21"));
        todoDTO.setFinished(true);
        log.info(todoDTO);

    }

//    @GetMapping("/ex5")
//    public String exs(){
//        return "ex5";
//    }


//    @GetMapping("/ex5")
//    public String exs(RedirectAttributes redirectAttributes){
//        log.info("ex5");
//        redirectAttributes.addAttribute("name", "hong");
//        return "ex5";
//    }
    @GetMapping("/ex5")
    public String exs(RedirectAttributes redirectAttributes){
        log.info("ex5");
        // addAttribute(키, 값) : 리다이렉트할 때 쿼리 스트링이 되는 값을 지정
        redirectAttributes.addAttribute("name", "hong");

        //addFlashAttribute(키, 값) : 일회용으로만 데이터를 전달하고 삭제되는 값을 지정
        redirectAttributes.addFlashAttribute("menu", "짜장");
        return "redirect:/ex6";
    }
//
    @GetMapping("/ex6")
    public void ex6(){
        log.info("ex6");
    }


    //@PostMapping
//    @PutMapping
//    @DeleteMapping
//    public void test(){
//
//    }





    @GetMapping(value = "/ex7")
    public void ex7(String p1, int p2){
        log.info(p1);
        log.info(p2);
    }

    @GetMapping(value = "/ex8")
    public void ex8(TodoDTO TodoDTO){
        TodoDTO.setDueDate(LocalDate.of(2020,3,4));
        TodoDTO.setFinished(true);
        TodoDTO.setTno(1L);
        TodoDTO.setWriter("hi");
        TodoDTO.setTitle("Test");
    }
}
