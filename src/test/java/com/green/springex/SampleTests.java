package com.green.springex;


import com.green.springex.sample.SampleService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

/* @ExtendWith(SpringExtension.class)은 JUnit 5에서 스프링 테스트 환경을 지원하기 위한 애노테이션입니다.

@ExtendWith(SpringExtension.class)을 사용하면 다음과 같은 기능을 사용할 수 있습니다:
스프링 테스트 애노테이션 사용: @Autowired, @MockBean, @SpyBean과 같은 스프링 테스트 애노테이션을 사용하여 테스트에서 스프링 빈을 주입하거나 목 객체를 생성할 수 있습니다.
테스트 설정 관리: @ContextConfiguration, @SpringBootTest와 같은 애노테이션을 사용하여 테스트를 위한 스프링 애플리케이션 컨텍스트를 설정할 수 있습니다.
테스트 실행 환경 제어: @ActiveProfiles, @DirtiesContext와 같은 애노테이션을 사용하여 테스트 실행 환경을 제어할 수 있습니다.
스프링 환경과의 통합: JUnit 5의 기능과 스프링의 기능을 효과적으로 결합하여 테스트를 작성할 수 있습니다. */

/*
@ContextConfiguration은 스프링 테스트에서 특정한 컨텍스트 구성을 지정하기 위해 사용되는 애노테이션입니다. 이 애노테이션을 사용하면 테스트 메서드가 실행될 때 스프링 애플리케이션 컨텍스트를 로드하고 설정할 수 있습니다.

@ContextConfiguration은 다양한 속성을 사용하여 특정한 방식으로 애플리케이션 컨텍스트를 로드할 수 있습니다. 가장 일반적으로 사용되는 속성은 다음과 같습니다:
locations: XML 파일이나 자바 설정 클래스의 경로를 지정합니다. 클래스 경로 루트부터 상대 경로로 지정할 수도 있고, 클래스 패스를 포함한 절대 경로로 지정할 수도 있습니다.
classes: 자바 설정 클래스를 지정합니다. 이 클래스를 기반으로 스프링 애플리케이션 컨텍스트가 설정됩니다.
initializers: 컨텍스트 초기화자를 지정합니다. 이는 컨텍스트가 생성될 때 적용되는 추가적인 설정을 제공할 때 사용됩니다.*/

@Log4j2
@ExtendWith(SpringExtension.class)
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
