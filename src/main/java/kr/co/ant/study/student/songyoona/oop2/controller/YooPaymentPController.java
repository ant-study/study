/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.student.songyoona.oop2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.service.YooPaymentService;
import lombok.extern.slf4j.Slf4j;


/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 11.
 */
@Controller
@Slf4j
public class YooPaymentPController {

    @Autowired
    YooPaymentService service;


    @ResponseBody
    @RequestMapping("/yoos/pay/card")
    public String paymentCard(PaymentInfo info) throws Exception{
        //service.paymentCard(info);
        service.compositePayment(info);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/yoos/pay/bank")
    public String paymentBank(PaymentInfo info) throws Exception{
        service.compositePayment(info);
        //service.paymentBank(info);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/yoos/pay/mobile")
    public String paymentMobile(PaymentInfo info) throws Exception{
        service.compositePayment(info);
        //service.paymentMobile(info);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/yoos/pay/total")
    public String paymentTotal(PaymentInfo info) throws Exception{
        service.paymentMobile(info);
        return "ok";
    }

}
