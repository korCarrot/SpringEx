package com.green.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {   //게시판이나 회원 정보 등도 페이징 처리가 필요할 수 있기 때문에 공통적인 처리를 위해서 제네릭으로 구성
    private int page;
    private int size;
    private int total;

    //조회 결과 현재 페이지의 첫 번호
    private int start;
    //조회 결과 현재 페이지의 마지막 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

//    생성자로 값을 받음
    @Builder(builderMethodName = "myMethod") //빌더(Builder) 클래스의 메서드 이름을 사용자가 직접 지정
    PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page=pageRequestDTO.getPage();
        this.size=pageRequestDTO.getSize();
        this.total=total;
        this.dtoList=dtoList;

        //조회 결과 현재 페이지의 마지막 번호
        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10;

        //조회 결과 현재 페이지의 첫 번호
        this.start = this.end - 9;

        //전체 글 마지막 페이지
        int last =  (int)(Math.ceil((total/(double)size)));

        //조회 결과의 마지막 번호 end 다시 체크 (총 마지막 페이지가 end보다 작을 수 없기 때문에 end>last가 true면 last(최종 페이지로))
        this.end =  end > last ? last : end;

        //이전 페이지 존재 여부 (첫번째 페이지 이상)
        this.prev = this.start > 1;

        //다음 페이지 존재 여부(총 페이지 수보다 작아야 다음 페이지 존재)
        this.next =  total > this.end * this.size;


    }

}
