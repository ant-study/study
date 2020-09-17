package kr.co.ant.study.moonjonghun.oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPGClient;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPayInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MoonPaymentFacadeServiceImpl implements MoonPaymentFacadeService{
	
	@Autowired
	MoonPGClient pg;
	
	@Autowired
	ObjectMapper mapper;

	/**
	 * 공통결제 프로세스
	 * @param Payment
	 * @return MoonReceiptVO
	 * */
	@Override
	public MoonReceiptVO doPayment(Payment p) {
		// 데이터의 유효성 체크
		try {
			p.validate();
		} catch (ValidateException e) {
			e.printStackTrace();
			return null;
		}
		
		try {
			//결제데이터 를 PG데이터로 복사
			//결제수단데이터를 PG데이터로 복사
			MoonPaymentInfo paymentInfo = p.convertPayToPG();
			
			//변환한 PG데이터를 JSON 데이터로 한번더 변환해준다.
			String jsonData = toJson(paymentInfo);
			
			//JSON데이터를 PGClient를 통해 전달 결제수행
			String result = pg.doPayment(jsonData, paymentInfo.getPaymentType());
			
			//결과 데이터를 VO로 전환
			MoonReceiptVO receipt = mapper.readValue(result, MoonReceiptVO.class);
			
			//결과값을 return
			if(receipt.isSuccess()) {
				return receipt;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 종합 결제정보를 가져와 field명을 snake_case로 전환한뒤
	 *  JsonString 형태로 변환한다.
	 * @param MoonPaymentInfo
	 * @return String
	 * */
	public String toJson(MoonPaymentInfo m) {
		// 결제수단 분석
		MoonPayInfo payInfo = m.getPayInfo();
		
		// 오브젝트명 셋팅
		// vo명을 SnakeCase로 가져온다. 
		String paymentName = payInfo.getClass().getSimpleName();
		String snakeName = ((SnakeCaseStrategy) PropertyNamingStrategy.SNAKE_CASE).translate(paymentName);
		
		//데이터를 
		ObjectNode mainNode = (ObjectNode) mapper.valueToTree(m);
		mainNode.putPOJO(snakeName, payInfo);
		
		return mainNode.toString();
	};

}
