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

import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.songyoona.oop.vo.CommonInfo;
import kr.co.ant.study.songyoona.oop.vo.YooPaySubModule;
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
    public String test(PaySample info) throws Exception{
        log.info("yooS  PaymentInfo ::: {}", info);

        String type = info.getPaymentType();

        // type에 따른 객체 생성
        CommonInfo c = new CommonInfo();
        //c.setTypeO(info);

        c.setPaySubModule(info);
        //c.setTypeO(c.setPaySubModule(type));
        // info 객체에 담기

        client.doPayment(mapper.writeValueAsString(c.getTypeO()));
        return "ok";
    }

}
