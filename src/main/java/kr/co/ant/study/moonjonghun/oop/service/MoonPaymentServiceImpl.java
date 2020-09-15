package kr.co.ant.study.moonjonghun.oop.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.co.ant.study.moonjonghun.oop.domain.MoonAccountInfoVO;
import kr.co.ant.study.moonjonghun.oop.domain.MoonCardInfoVO;
import kr.co.ant.study.moonjonghun.oop.domain.MoonMobileInfoVO;
import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.validation.ValidatePayment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoonPaymentServiceImpl implements MoonPaymentService{
	
	@Autowired
	ValidatePayment payment;
	
	public <T extends MoonPaymentVO> Object doPayment(String json, T obj) throws Exception{

		//결제용 DTO를 통해 들어온게 아님
		if(!obj.getClass().getName().equals("MoonPaymentVO")) 
			throw new Exception("결제정보에 오류가 발생하였습니다.");		
		
		//결제전 결제수단의 유효성을 체크한다.

		Class clazz = obj.getClass();
		Field fPaymentType = clazz.getDeclaredField("paymentType");
		Field fAmount = clazz.getDeclaredField("amount");
		
		Method getPType = clazz.getDeclaredMethod("get" + StringUtils.capitalize(fPaymentType.getName()));
		Method getAmount = clazz.getDeclaredMethod("get" + StringUtils.capitalize(fAmount.getName()));
		String paymentType = (String) getPType.invoke(obj);
		int amount = (int) getAmount.invoke(obj);
		
		String payWay = "";
		switch (paymentType){
			case "CARD" :
					Field fCardInfo = clazz.getDeclaredField("cardInfo");
					Method getCardInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fCardInfo.getName()));
					MoonCardInfoVO cardInfo = (MoonCardInfoVO) getCardInfo.invoke(obj);
					
					payment.validate(cardInfo);
					payWay += "신용카드";
				break;
			case "MOBILE" :
					Field fMobileInfo = clazz.getDeclaredField("mobileInfo");
					Method getMobileInfo = clazz.getDeclaredMethod("get"+StringUtils.capitalize(fMobileInfo.getName()));
					MoonMobileInfoVO mobileInfo = (MoonMobileInfoVO) getMobileInfo.invoke(obj);
					
					payment.validate(mobileInfo);
					payWay += "휴대폰결제"; 
				break;
			case "BANK" :		
					Field fBankInfo = clazz.getDeclaredField("accountInfo");
					Method getBankInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fBankInfo.getName()));
					MoonAccountInfoVO accountInfo = (MoonAccountInfoVO) getBankInfo.invoke(obj);
					
					payment.validate(accountInfo);
					payWay += "계좌이체";
				break;
		}
		
		//유효한 결제수단이라면  
		log.info("##########지불하신 결제 수단은 "+payWay+"로 총 결제금액은 "+amount+"원 입니다.#######################################");
		
		
		return null;
	} 
	

}
