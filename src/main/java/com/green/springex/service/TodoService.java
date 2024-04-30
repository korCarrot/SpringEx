package com.green.springex.service;

import com.green.springex.dto.PageRequestDTO;
import com.green.springex.dto.PageResponseDTO;
import com.green.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);

    //페이징 처리 후 조회
    PageResponseDTO<TodoDTO> getPagingList(PageRequestDTO pageRequestDTO);
}
