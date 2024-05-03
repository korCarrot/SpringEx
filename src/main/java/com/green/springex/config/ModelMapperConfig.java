package com.green.springex.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //해당 클래스가 스프링빈(Bean)에 대한 설정을 하는 클래스임을 명시
public class ModelMapperConfig {

    @Bean // 해당 메소드의 실행 결과로 반환된 객체를 스프링의 빈(Bean)으로 등록시키는 역할
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
                return modelMapper;
    }
}

/* setFieldMatchingEnabled(true)은 필드 이름을 사용하여 매핑을 수행하도록 설정합니다. 이렇게 하면 소스 객체와 대상 객체 간에 동일한 이름을 가진 필드가 있을 때 자동으로 매핑됩니다.

setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)은 private 접근 수준의 필드에 액세스할 수 있도록 설정합니다. 이렇게 하면 ModelMapper가 private 필드에 액세스하여 값을 읽고 설정할 수 있습니다.

setMatchingStrategy(MatchingStrategies.STRICT)은 매핑 전략을 설정합니다. STRICT 매칭 전략은 매핑되지 않은 필드가 있는 경우 에러를 발생시키므로, 모든 소스 필드가 대상 필드와 엄격하게 일치해야 합니다. */
