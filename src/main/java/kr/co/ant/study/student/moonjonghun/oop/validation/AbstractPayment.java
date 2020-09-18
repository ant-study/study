package kr.co.ant.study.student.moonjonghun.oop.validation;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.student.moonjonghun.oop.domain.Payment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractPayment implements Payment{
	
	protected MoonPaymentVO paymentVO;
	protected Validation valid;
	
	public AbstractPayment(MoonPaymentVO vo, Validation v) {
		this.paymentVO = vo;
		this.valid = v;
	}
	
	/**
	 * 결제정보를 PG사 전송을 위한 VO로 convert
	 * */
	@Override
	public <T, U> Object convertToPG(T pg, U vo) throws Exception {
		PropertyUtils.copyProperties(pg, vo);
		return pg;
	}
	
	
	@Override
	public void logging(String type, int amt) {
		log.info("주문하신 결제수단은 {}로 주문금액은 {}원 입니다.", type, amt);
		
	}

}
