package com.green.springex.mapper;

import com.green.springex.mapper.TimeMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class) //@ExtendWith는 주석이 달린 테스트 클래스 또는 테스트 메서드에 대한 확장을 등록하는 데 사용되는 반복 가능한 주석
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {


    @Autowired(required = false) //(required = false) 해당 객체를 주입받지 못하더라도 예외가 발생하지 않음
    private TimeMapper timeMapper;

    @Test
    public void testTime(){
        log.info("시간 : "+timeMapper.getTime());
    }
}
