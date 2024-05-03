package com.green.springex.service;


import com.green.springex.dto.PageRequestDTO;
import com.green.springex.dto.PageResponseDTO;
import com.green.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO=TodoDTO.builder().title("Test2")
                .dueDate(LocalDate.now()).writer("user01").build();

        todoService.register(todoDTO);
    }

    @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO=PageRequestDTO.builder().page(1).size(10).build();
        log.info("pageRequestDTO : "+ pageRequestDTO);
                PageResponseDTO responseDTO=todoService.getPagingList(pageRequestDTO);  //pageResponseDTO 반환
                responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));
    }
}
