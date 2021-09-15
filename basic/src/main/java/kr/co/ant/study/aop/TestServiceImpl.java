package kr.co.ant.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void testMethod(String s) {
        log.info("TestServiceImpl :: {}", s);
    }
}
