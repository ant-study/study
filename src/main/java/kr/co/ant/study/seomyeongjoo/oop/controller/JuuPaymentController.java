package kr.co.ant.study.seomyeongjoo.oop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import kr.co.ant.study.seomyeongjoo.oop.domain.PaySample;
import kr.co.ant.study.seomyeongjoo.oop.domain.PaySubModule;
import kr.co.ant.study.seomyeongjoo.oop.pg.JuuANTPGClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class JuuPaymentController {

    @Autowired
    JuuANTPGClient client;

    @Autowired
    ObjectMapper mapper;

    @ResponseBody
    @RequestMapping("/testMJ")
    public String test(PaySample info) throws JsonProcessingException {
        log.info("PaymentInfo ::: {} ::::", info);
        client.doPayment(mapper.writeValueAsString(info));
        return "ok";
    }
}
