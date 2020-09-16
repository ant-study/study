/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.songyoona.oop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.songyoona.oop.vo.CommonInfo;
import kr.co.ant.study.songyoona.oop.vo.YooPaymentInfoVo;
import kr.co.ant.study.songyoona.oop2.service.YooPaymentFacade;
import kr.co.ant.study.songyoona.oop2.service.YooPaymentService;
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


    @Autowired
    YooPaymentService service;

    @ResponseBody
    @RequestMapping("/yoos/test1")
//    public <T>String test(DataGroup<?> info) throws Exception{
    public <T> String test1(YooPaymentInfoVo info) throws Exception{
        log.info("yooS  PaymentInfo ::: {}", info);

        String type = info.getPaymentType();
        // type에 따른 info객체 생성은  tobe... enum YooPaySubModule 참고해서 나중에..


        // PG사로 보낼 VO 생성 : info 객체에 담기
        CommonInfo c = new CommonInfo();
        //PG사로 보낼 정보 : c.getTypeO()
        c.setTypeObj(info);

        //Json 변환 : mapper.writeValueAsString
        client.doPayment(mapper.writeValueAsString(c));
        return "ok";
    }


}
