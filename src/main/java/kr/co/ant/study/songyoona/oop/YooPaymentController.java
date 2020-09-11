/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.songyoona.oop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.domain.PaySample;
import kr.co.ant.study.oop.pg.ANTPGClient;
import lombok.extern.slf4j.Slf4j;


/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 11.
 */
@Controller
@Slf4j
public class YooPaymentController {
    @Autowired
    ANTPGClient client;

    @Autowired
    ObjectMapper mapper;

    @ResponseBody
    @RequestMapping("/yoos/test")
    public String test(PaySample info) throws JsonProcessingException{
        log.info("yooS  PaymentInfo ::: {}", info);
        client.doPayment(mapper.writeValueAsString(info));
        return "ok";
    }

}
