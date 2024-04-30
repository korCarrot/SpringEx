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

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    public int getSkip(){
        return (page-1)*10;
    }

    // 글 조회 후 해당 글이 있는 위치를 문자열로 저장
    private String link;


    // 키워드 검색을 위한 변수 선언

    private String[] types;

    private String keyword;

    private boolean finished;

    private LocalDate from;

    private LocalDate to;






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