package com.green.springex.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default    //@Builder.Default 어노테이션을 이용하여 필드의 기본값을 설정
    @Min(value = 1)     //@Min(value = 1) 최솟값이 1
    @Positive           //@Positive 양수여야 한다는 것을 나타냄
    private int page = 1;

    @Builder.Default
    @Min(value = 10)    //@Min(value = 10)과 @Max(value = 100) 어노테이션은 각각 최솟값이 10이고 최댓값이 100이라는 제약을 설정
    @Max(value = 100)
    @Positive
    private int size = 10;

//    getSkip() 메서드는 현재 페이지와 페이지 크기를 고려하여 스킵할 레코드 수를 계산하여 반환하는 메서드
    public int getSkip(){
        return (page-1)*10;
    }

    // 글 조회 후 해당 글이 있는 위치를 문자열로 저장
    private String link;


    // 키워드 검색을 위한 변수 선언

    private String[] types; //검색 종류

    private String keyword; //검색 키워드, 문자열

    private boolean finished; //완료 여부

    private LocalDate from; //특정 기간

    private LocalDate to; //특정 기간

// <검색과 필터링 구분>
//    검색 : OR조건. A 혹은 B 혹은 C
//    필터링 : And조건. A인 동시에 B에도 해당(A&B)




    public String getLink() {
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            //글 조회 링크시에 조건 검색 저장
            if(finished){
                builder.append("&finished=on");
            }

            if(types != null && types.length > 0){
                for (int i = 0; i < types.length ; i++) {
                    builder.append("&types=" + types[i]);
                }
            }


            if(keyword != null){
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if(from != null){
                builder.append("&from=" + from.toString());
            }

            if(to != null){
                builder.append("&to=" + to.toString());
            }


            link = builder.toString();
        }
        return link;
    }


    @Override
    public String toString() {
        return "PageRequestDTO{" +
                "page=" + page +
                ", size=" + size +
                ", link='" + link + '\'' +
                ", types=" + Arrays.toString(types) +
                ", keyword='" + keyword + '\'' +
                ", finished=" + finished +
                ", from=" + from +
                ", to=" + to +
                '}';
    }


    public boolean checkType(String type){
        if(types == null || types.length == 0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }

}