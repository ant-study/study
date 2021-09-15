package kr.co.ant.study.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = {AOPSampleService.class, LoggingAspect.class, TestServiceImpl.class})
@EnableAspectJAutoProxy(exposeProxy = true)
class AOPSampleServiceTest {

    @Autowired
    AOPSampleService service;

    @Autowired
    TestService testService;

    @Test
    void test(){
        /*
         * 디버깅모드로 testService 객체와 AOPSampleService 객체 실체 비교
         *  - testService는 JDK DynamicProxy 객체
         *  - AOPSampleService는 CGLIB Proxy 객체
         */
        testService.testMethod("test");
        service.voidService("test");
        String test = service.service("test");
        assertThat(test)
                .isEqualTo("test_return");
        assertThatThrownBy(() -> service.exceptionService())
                .isInstanceOf(RuntimeException.class);
    }
}