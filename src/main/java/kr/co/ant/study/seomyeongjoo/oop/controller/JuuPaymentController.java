package kr.co.ant.study.seomyeongjoo.oop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;


import kr.co.ant.study.seomyeongjoo.oop.domain.PaymentInfo;
import kr.co.ant.study.seomyeongjoo.oop.pg.JuuANTPGClient;
import kr.co.ant.study.seomyeongjoo.oop.service.JuuPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/mj")
public class JuuPaymentController {

    @Autowired
    JuuANTPGClient client;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    JuuPaymentService service;

    @ResponseBody
    @RequestMapping("/card")
    public String cardPayment(PaymentInfo info) throws Exception {
        log.info("PaymentInfo ::: {} ::::", info.toString());
        service.cardPay(info);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/mobile")
    public String mobilePayment(PaymentInfo info) throws Exception {
        log.info("PaymentInfo ::: {} ::::", info.toString());
        service.mobilePay(info);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/bank")
    public String bankAccountPayment(PaymentInfo info) throws Exception {
        log.info("PaymentInfo ::: {} ::::", info.toString());
        service.bankPay(info);
        return "ok";
    }

}
