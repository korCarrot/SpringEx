package com.green.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@ToString
//@RequiredArgsConstructor
public class SampleService {
    @Autowired
    private SampleDAO sampleDAO;

//    @Qualifier("normal")
//    private final SampleDAO sampleDAO;

    //객체 주입 방식1(필드 주입) @Autowired
    //객체 주입 방식2(생성자 주입) @Autowired 생략, 필드에 final 추가, 클래스에  @RequiredArgsConstructor 추가


}
