package kr.co.ant.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * interface 구현체가 아니여서 AOP동작은 CGLIB Proxy가 생성됨
 */
@Slf4j
@Service
public class AOPSampleService {

    public String service(String s){
      log.info("Service s length = {}", s.length());

        //@EnableAspectJAutoProxy(exposeProxy = true) exposeProxy가 true여야 가능
        Object o = AopContext.currentProxy();
        AOPSampleService o1 = (AOPSampleService) o;
        o1.voidService(s);


        return s += "_return";
    }

    public void voidService(String s){
        log.info("Void Service s length = {}", s.length());
    }

    public void exceptionService(){
        throw new RuntimeException("exception service");
    }
}
