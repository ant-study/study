/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.songyoona.oop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.songyoona.oop.strategy.FixLengthStrategy;
import kr.co.ant.study.songyoona.oop.strategy.MoreLengthStrategy;
import kr.co.ant.study.songyoona.oop.vo.BankInfo;
import kr.co.ant.study.songyoona.oop.vo.CommonInfo;
import kr.co.ant.study.songyoona.oop.vo.Validating;
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


    //<T> T toVO(Map<String, ?> map, Class<T> clazz)
    @ResponseBody
    @RequestMapping("/yoos/test")
//    public <T>String test(DataGroup<?> info) throws Exception{
    public String test(PaySample info) throws Exception{
        log.info("yooS  PaymentInfo ::: {}", info);
//        T t = (T) new PaySample();

        String type = info.getPaymentType();

        //bank.setValidate(new MoreLengthStrategy());
        // type에 따른 객체 생성
        CommonInfo c = new CommonInfo();
        //c.setTypeO(info);

        c.setPaySubModule(info);
        //c.setTypeO(c.setPaySubModule(type));
        // info 객체에 담기


        // 숫자 자리수 체크


        client.doPayment(mapper.writeValueAsString(c.getTypeO()));
        return "ok";
    }

}
