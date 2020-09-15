package kr.co.ant.study.moonjonghun.oop.pg;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.moonjonghun.oop.domain.MoonAccountInfo;
import kr.co.ant.study.moonjonghun.oop.domain.MoonCardInfo;
import kr.co.ant.study.moonjonghun.oop.domain.MoonMobileInfo;
import kr.co.ant.study.moonjonghun.oop.domain.MoonPayDTO;
import kr.co.ant.study.moonjonghun.oop.validation.validatePayment;

@Component
public class MoonPGClient implements validatePayment{
	
	@Autowired
	ObjectMapper mapper;

	public <T extends MoonPayDTO> Object doPayment(String json, T obj) throws Exception{
		
		//결제용 DTO를 통해 들어온게 아님
//		if(!obj.getClass().getName().equals("MoonPayDTO")) 
//			throw new Exception("결제정보에 오류가 발생하였습니다.");		
		
		//결제전 결제수단의 유효성을 체크한다.

		Class clazz = obj.getClass();
		Field fPaymentType = clazz.getDeclaredField("paymentType");
		
		Method getPType = clazz.getDeclaredMethod("get" + StringUtils.capitalize(fPaymentType.getName()));

		String paymentType = (String) getPType.invoke(obj);
		
		
		switch (paymentType){
			case "CARD" :
					Field fCardInfo = clazz.getDeclaredField("cardInfo");
					Method getCardInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fCardInfo.getName()));
					MoonCardInfo cardInfo = (MoonCardInfo) getCardInfo.invoke(obj);
					
					validate(cardInfo);
				break;
			case "MOBILE" :
					Field fMobileInfo = clazz.getDeclaredField("mobileInfo");
					Method getMobileInfo = clazz.getDeclaredMethod("get"+StringUtils.capitalize(fMobileInfo.getName()));
					MoonMobileInfo mobileInfo = (MoonMobileInfo) getMobileInfo.invoke(obj);
					validate(mobileInfo);
				break;
			case "BANK" :		
					Field fBankInfo = clazz.getDeclaredField("accountInfo");
					Method getBankInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fBankInfo.getName()));
					MoonAccountInfo accountInfo = (MoonAccountInfo) getBankInfo.invoke(obj);
					
					validate(accountInfo);
				break;
		}
		
		//유효한 결제수단이라면  
		
		return null;
	}
	
	@Override
	public void validate(MoonCardInfo mc) throws Exception{
		String cardNo = mc.getCardNo();
		if(cardNo.length() != 16) {
			throw new Exception("카드번호가 16자리를 충족하지 못함");
		}
	}

	@Override
	public void validate(MoonMobileInfo mm) throws Exception {
		String mobileNo = mm.getMobileNo();
		if(mobileNo.length() != 10) {
			throw new Exception("휴대폰번호가 10자리를 충족하지 못함");
		}
	}

	@Override
	public void validate(MoonAccountInfo mb) throws Exception {
		String accountNo = mb.getAccountNo();
		if(accountNo.length() != 20 ) {
			throw new Exception("계좌번호가 20자를 충족하지 못함");
		}
	}
	
	
}
