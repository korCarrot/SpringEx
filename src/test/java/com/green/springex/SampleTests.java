package com.green.springex;


import com.green.springex.sample.SampleService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class) //@ExtendWith는 주석이 달린 테스트 클래스 또는 테스트 메서드에 대한 확장을 등록하는데 사용되는 반복 가능한 주석
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;


    @Test
    public void testService1(){
        log.info("sampleService 객체# :" + sampleService);
        Assertions.assertNotNull(sampleService);

    }

    //DB 연결 테스트
    @Test
    public void testConnect() throws Exception{
        Connection connection =dataSource.getConnection();
        log.info("커넥션 객체 : " + connection);
        Assertions.assertNotNull(connection);
        connection.close();
    }




}
