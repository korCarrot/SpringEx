package com.green.springex.mapper;

import com.green.springex.domain.TodoVO;
import com.green.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    //글 삽입
    void insert(TodoVO todoVO);

    //모든 글 조회

    List<TodoVO> selectAll();

    //글 조회
    TodoVO selectOne(Long tno);

    //글 삭제
    void delete(Long tno);

    //글 수정
    void update(TodoVO todoVO);

    //페이징을 적용한 전체 글 조회
    List<TodoVO> selectPagingList(PageRequestDTO pageRequestDTO);

    //전체 글 개수 조회
    int getCount(PageRequestDTO pageRequestDTO);

    //조건에 따른 검색 후 조회
    List<TodoVO> selectConditionList(PageRequestDTO pageRequestDTO);


}
