package kr.co.ant.study.moonjonghun.oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPGClient;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MoonPaymentFacadeServiceImpl implements MoonPaymentFacadeService{
	
	@Autowired
	MoonPGClient pg;

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
			
			
			//JSON데이터를 PGClient를 통해 전달
			
			//결과값을 return
			return null;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
	}

}
