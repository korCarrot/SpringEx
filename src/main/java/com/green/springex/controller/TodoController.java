package com.green.springex.controller;

import com.green.springex.dto.PageRequestDTO;
import com.green.springex.dto.PageResponseDTO;
import com.green.springex.dto.TodoDTO;
import com.green.springex.service.TodoService;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    @Autowired
    private TodoService todoService;

    //전체 글 조회
//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("list --- GET ----");
//
//        model.addAttribute("dtoList", todoService.getAll());
//
//    }

    //전체 글 조회(페이징 적용)
    /* Java Beans와 @ModelAttribute : 스프링 MVC의 컨틀롤러는 파라미터로 getter/setter를 이용하는 Java Beans 형식의
                                     사용자 정의 클래스가 파라미터인 경우 자동으로 화면까지 객체를 전달합니다. */
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model  model) {
        log.info("list --- GET#----");
        log.info("pageRequestDTO" +pageRequestDTO);
        log.info("pageRequestDTO + link" +pageRequestDTO.getLink());

        //잘못된 파라미터값들이 들어오는 경우 기본값인 page 1, size는 10으로 고정된 값을 처리하도록 함
        if(bindingResult.hasErrors()){
            pageRequestDTO=PageRequestDTO.builder().build();
        }
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getPagingList(pageRequestDTO);
        model.addAttribute("responseDTO", pageResponseDTO);
        log.info(pageResponseDTO);

    }



    //글 삽입 화면
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }



    //글 삽입
    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POSt todo register.......");

        log.info(bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            //log.info(bindingResult.hasErrors()+"" + bindingResult+redirectAttributes);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        todoService.register(todoDTO);

        log.info("todoDTO : " + todoDTO);
        return "redirect:/todo/list";
    }

    //글 조회
//    @GetMapping("/read")
//    public void read(Long tno, Model model){
//        log.info("조회하고자 하는 글 번호" +tno );
//        TodoDTO todoDTO=todoService.getOne(tno);
//        log.info("글 조회 : " + todoDTO);
//        model.addAttribute("dto", todoDTO);
//    }


    //글 조회
    @GetMapping("/read")
    public void read(Long tno,PageRequestDTO pageRequestDTO, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO );
    }



    //수정/삭제 화면, 수정/삭제하고자 하는 기존의 글을 가져와야 함
    @GetMapping("/modify")
    public void modifyGET(Long tno,PageRequestDTO pageRequestDTO, Model model){
        log.info("수정/삭제하고자 하는 글 번호" +tno );
        //수정/삭제하고자 하는 기존의 글을 가져와야 함
        TodoDTO todoDTO =todoService.getOne(tno);
        log.info("todoDTO" + todoDTO);
        model.addAttribute("dto", todoDTO);

    }



    //서버에서 수정
    @PostMapping("/modify")
    public String modifyPOST(PageRequestDTO pageRequestDTO,@Valid TodoDTO todoDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        //log.info("기존 todoDTO"  + todoDTO);
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
//            return "redirect:/todo/modify";
//        }
        todoService.modify(todoDTO);
        log.info("수정후  todoDTO"  + todoDTO);

//        redirectAttributes.addAttribute("page",pageRequestDTO.getPage() );
//        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());

        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read";
    }



    //글 삭제
    @PostMapping("/delete")
    public String deletePOST(PageRequestDTO pageRequestDTO, TodoDTO todoDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        log.info("삭제하고자 하는 글 :" + todoDTO);
//

        todoService.remove(todoDTO.getTno());

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage() );
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());

        return  "redirect:/todo/list";
    }



}