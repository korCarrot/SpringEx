package com.green.springex.service;

import com.green.springex.domain.TodoVO;
import com.green.springex.dto.PageRequestDTO;
import com.green.springex.dto.PageResponseDTO;
import com.green.springex.dto.TodoDTO;
import com.green.springex.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info("modelMapper : " + modelMapper);

        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);
        log.info("todoVO"+todoVO);

        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {

// List<TodoDTO> dtoList = todoMapper.selectAll().stream(): todoMapper 객체를 사용하여 데이터베이스에서 모든 Todo 데이터를 가져옵니다. 그 후에는 이 데이터를 스트림으로 변환
// .map((vo)->{return modelMapper.map(vo,TodoDTO.class);}): map 메서드를 사용하여 각 Todo 엔티티를 TodoDTO로 변환
// .collect(Collectors.toList()): 변환된 각 TodoDTO 객체를 리스트로 수집합니다. Collectors.toList()는 스트림의 각 요소를 리스트로 수집하는 컬렉터
        List<TodoDTO> dtoList =todoMapper.selectAll().stream()
                .map((vo)->{return modelMapper.map(vo,TodoDTO.class);})
                .collect(Collectors.toList());

        return dtoList;
//   dtoList에는 TodoDTO(tno=1032, title='한글입력', dueDate=2024-06-06, writer='user00', finished=false),
//              TodoDTO(tno=1031, title='짜장', dueDate=2025-05-20, writer='user02', finished=false) 등의 모습으로 담겨있음
    }

    @Override
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO=todoMapper.selectOne(tno);
        TodoDTO todoDTO=modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    //글 삭제
    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    //글 수정
    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO=modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }

    //페이징 처리
    @Override
    public PageResponseDTO<TodoDTO> getPagingList(PageRequestDTO pageRequestDTO) {
        log.info("pageRequestDTO : " + pageRequestDTO); //기본값

        //tbl_todo의 값들을 리스트로.
        List<TodoVO> voList = todoMapper.selectPagingList(pageRequestDTO);
        log.info("voList : " + voList);

        //VO List를 DTO List로
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        log.info("dtoList : " + dtoList);

        //글의 전체 개수(->마지막 페이지, 다음 페이지 존재 여부를 구하기 위해 필요)
        int total = todoMapper.getCount(pageRequestDTO);
        log.info("total : " + total);

//빌더 메소드를 통해 생성자에 값을 넣음(생성자에서 PageResponseDTO의 모든 필드 값들을 설정함)
        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>myMethod()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

//        생성자에서 설정되는 내용들.
//log.info("조회 결과 현재 페이지의 마지막 번호 : " + pageResponseDTO.getEnd());
//        log.info("조회 결과 현재 페이지의 시작 번호 : " + pageResponseDTO.getStart());
//        log.info("마지막 페이지 last : " + (int)(Math.ceil((total/(double)10))));
//        log.info("조회 결과의 마지막 번호 end 다시 체크");
//log.info(pageResponseDTO.getEnd() > (int)(Math.ceil((total/(double)10)))? (int)(Math.ceil((total/(double)10))) : pageResponseDTO.getEnd());
//log.info("이전 페이지 존재 여부 prev: " + ((pageResponseDTO.getStart())>1));
//        log.info("다음 페이지 존재 여부 next: " + (total > pageResponseDTO.getEnd() * 10 ));
        return pageResponseDTO;
    }

}
