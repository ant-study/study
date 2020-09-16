package kr.co.ant.study.songyoona.oop2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.co.ant.study.oop.service.PaymentException;
import kr.co.ant.study.songyoona.oop2.payment.YooPayment;
import kr.co.ant.study.songyoona.oop2.pg.ANTPaymentResponse;
import kr.co.ant.study.songyoona.oop2.pg.YooANTPGClient;
import kr.co.ant.study.songyoona.oop2.pg.vo.ANTPayInfo;
import kr.co.ant.study.songyoona.oop2.pg.vo.ANTPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class YooPaymentFacade {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    YooANTPGClient client;
	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param p
	 */
	public ANTPaymentResponse doPayment(YooPayment p) throws Exception{
		//Validate 체크 => 오류인경우 예외 처리
	    try {
	        p.validate();

	    }catch(ValidateException e) {
            log.error("유효성 검사 오류입니다. {}", e.getMessage());
            throw e;
	    }

		//Logging
	    p.logging();

		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
	    ANTPaymentInfo info = p.convertToANTPaymentInfo();

		//변환된값 Json을 다시 변환
	    String json = toJson(info);

		//PG 호출
	    String result = client.doPayment(json);

		//결과 처리
	    ANTPaymentResponse response = mapper.readValue(result, ANTPaymentResponse.class);

	    if(response.isSuccess()) {
	        return response;
	    }else {
	        throw new PaymentException(response.getMessage());
	    }
	}

	public String toJson(ANTPaymentInfo paymentInfo) throws Exception{
	    ANTPayInfo payInfo = paymentInfo.getPayInfo();

	    String subName = payInfo.getClass().getSimpleName();
	    String snakeName = ((SnakeCaseStrategy)PropertyNamingStrategy.SNAKE_CASE).translate(subName);

	    ObjectNode mainNode = mapper.valueToTree(paymentInfo);
	    mainNode.putPOJO(snakeName, payInfo);

	    return mainNode.toString();
	}
}
