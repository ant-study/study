package kr.co.ant.study.moonjonghun.oop.validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PaymentImpl implements Payment{
	
	
	//확장성이 부족하여 사용하지 않겠음
	@Override
	public <T> String validate(T obj) throws Exception{
		Class paymentClazz = obj.getClass();
		Field[] fs = paymentClazz.getDeclaredFields();
		for(Field f : fs) {
			String sf = f.getName();
			if(sf.equals("cardNo")) {
				Method getCardNo = paymentClazz.getDeclaredMethod("getCardNo");
				String cardNo = (String) getCardNo.invoke(obj);
				if(cardNo.length() != 16) {
					throw new ValidateException("카드번호가 16자리를 충족하지 못함");
				}
				return cardNo;
			}
			
			if(sf.equals("mobileNo")) {
				Method getMobileNo = paymentClazz.getDeclaredMethod("getMobileNo");
				String mobileNo = (String) getMobileNo.invoke(obj);
				if(mobileNo.length() < 10) {
					throw new ValidateException("휴대폰번호가 10자리를 충족하지 못함");
				}
				return mobileNo;
			}
			
			if(sf.equals("AccountNo")) {
				Method getAccountNo = paymentClazz.getDeclaredMethod("getAccountNo");
				String accountNo = (String) getAccountNo.invoke(obj);
				if(accountNo.length() != 20 ) {
					throw new ValidateException("계좌번호가 20자를 충족하지 못함");
				}
				return accountNo;
			}
			
		}
		
		return null;
	}
	
	@Override
	public void fixedLengthValidate(String s, int fixedLength) throws Exception {
		if(s.length() != fixedLength) {
			throw new ValidateException(fixedLength + "자리가 아닙니다.");
		}
	}

	@Override
	public void minLengthValidate(String s, int minLength) throws Exception {
		if(s.length() < minLength) {
			throw new ValidateException(minLength + "자리보다 커야합니다.");
		}
	}

	/**
	 * 결제정보를 PG사 전송을 위한 VO로 convert
	 * */
	@Override
	public <T, U> Object convertToPG(T pg, U vo) throws Exception {
		PropertyUtils.copyProperties(pg, vo);
		return pg;
	}

	
	

}