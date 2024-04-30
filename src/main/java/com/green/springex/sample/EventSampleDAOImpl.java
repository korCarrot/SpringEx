package com.green.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
//@Primary //여러 후보가 자격을 갖춘 경우 Bean에 우선 순위를 부여해야 함을 나타냅니다. 후보 중에 정확히 하나의 '기본' Bean이 존재하는 경우 이는 자동 연결 값이 됩니다.
public class EventSampleDAOImpl implements SampleDAO{
}
