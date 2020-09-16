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
import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.payment.BankPayment;
import kr.co.ant.study.moonjonghun.oop.payment.CardPayment;
import kr.co.ant.study.moonjonghun.oop.payment.MobilePayment;
import kr.co.ant.study.moonjonghun.oop.pg.BankAccountInfo;
import kr.co.ant.study.moonjonghun.oop.pg.CardInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MobileInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MoonBankPaymentInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MoonCardPaymentInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MoonMobilePaymentInfo;
import kr.co.ant.study.moonjonghun.oop.validation.FixedLengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.MinlengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.PaymentImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoonPaymentServiceImpl implements MoonPaymentService{
	
//	@Autowired
//	Payment payment;
	
	@Autowired
	MoonPaymentFacadeService facadeService;
//	
//	@Autowired
//	FixedLengthValidation fixedLengthValid;
//	
//	@Autowired
//	MinlengthValidation MinLengthValid;
	
	
	//이건 너무 재사용성이 떨어진다.
//	public <T extends MoonPaymentVO> Object doPayment(String json, T obj) throws Exception{
//
//		//결제용 DTO를 통해 들어온게 아님
////		if(!obj.getClass().getName().equals("MoonPaymentVO"))
////			throw new Exception("결제정보에 오류가 발생하였습니다.");		
//		
//		//결제전 결제수단의 유효성을 체크한다.
//
//		Class clazz = obj.getClass();
//		Field fPaymentType = clazz.getDeclaredField("paymentType");
//		Field fAmount = clazz.getDeclaredField("amount");
//		
//		Method getPType = clazz.getDeclaredMethod("get" + StringUtils.capitalize(fPaymentType.getName()));
//		Method getAmount = clazz.getDeclaredMethod("get" + StringUtils.capitalize(fAmount.getName()));
//		String paymentType = (String) getPType.invoke(obj);
//		int amount = (int) getAmount.invoke(obj);
//		
//		String payWay = "";
//		Object pgData = null;
//		switch (paymentType){
//			case "CARD" :
//					Field fCardInfo = clazz.getDeclaredField("cardPayInfo");
//					Method getCardInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fCardInfo.getName()));
//					MoonCardInfoVO cardInfoVo = (MoonCardInfoVO) getCardInfo.invoke(obj);
//					
////					payment.validate(cardInfo); //확장성 부족
//					fixedLengthValid.validate(cardInfoVo.getCardNo(), 16);
//					
//					//PG사로 넘겨야할 VO로 변환
//					MoonCardPaymentInfo cardPaymentInfo = new MoonCardPaymentInfo();
//					cardPaymentInfo = (MoonCardPaymentInfo) payment.convertToPG(cardPaymentInfo,obj);
//					
//					CardInfo cardInfo = new CardInfo();
//					cardInfo = (CardInfo) payment.convertToPG(cardInfoVo, cardInfo);
//					
//					cardPaymentInfo.setCardInfo(cardInfo);
//					
//					payWay += "신용카드";
//				break;
//			case "MOBILE" :
//					Field fMobileInfo = clazz.getDeclaredField("mobilePayInfo");
//					Method getMobileInfo = clazz.getDeclaredMethod("get"+StringUtils.capitalize(fMobileInfo.getName()));
//					MoonMobileInfoVO mobileInfoVo = (MoonMobileInfoVO) getMobileInfo.invoke(obj);
//					
////					payment.validate(mobileInfo); //확장성 부족
//					MinLengthValid.validate(mobileInfoVo.getMobileNo(), 10);
//					
//					//PG사로 넘겨야할 VO로 변환
//					MoonMobilePaymentInfo mobilePaymentInfo = new MoonMobilePaymentInfo();
//					mobilePaymentInfo = (MoonMobilePaymentInfo) payment.convertToPG(mobilePaymentInfo, obj);
//				
//					
//					MobileInfo mobileInfo = new MobileInfo();
//					mobileInfo = (MobileInfo) payment.convertToPG(mobileInfo, mobileInfoVo);
//					
//					mobilePaymentInfo.setMobileInfo(mobileInfo);
//					
//					payWay += "휴대폰결제"; 
//				break;
//			case "BANK" :		
//					Field fBankInfo = clazz.getDeclaredField("accountPayInfo");
//					Method getBankInfo = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(fBankInfo.getName()));
//					MoonAccountInfoVO accountInfo = (MoonAccountInfoVO) getBankInfo.invoke(obj);
//					
////					payment.validate(accountInfo); //확장성 부족
//					fixedLengthValid.validate(accountInfo.getAccountNo(), 20);
//					
//					//PG사로 넘겨야할 VO로 변환
//					MoonBankPaymentInfo bankPaymentInfo = new MoonBankPaymentInfo();
//					bankPaymentInfo = (MoonBankPaymentInfo) payment.convertToPG(bankPaymentInfo, obj);
//					
//					BankAccountInfo bankAccountInfo = new BankAccountInfo();
//					bankAccountInfo = (BankAccountInfo) payment.convertToPG(bankAccountInfo, accountInfo);
//					
//					bankPaymentInfo.setAccountInfo(bankAccountInfo);
//					
//					
//					payWay += "계좌이체";
//				break;
//		}
//		
//		
//		//유효한 결제수단이라면  
//		log.info("##########지불하신 결제 수단은 "+payWay+"로 총 결제금액은 "+amount+"원 입니다.#######################################");
//		
//		
//		return null;
//	}

	@Override
	public MoonReceiptVO cardPayment(MoonPaymentVO vo) {
		// TODO Auto-generated method stub
		CardPayment cardPayment = new CardPayment(vo, new FixedLengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(cardPayment);
		return paymentResponse;
	}

	@Override
	public MoonReceiptVO bankAccountPayment(MoonPaymentVO vo) { 
		BankPayment bankPayment = new BankPayment(vo, new FixedLengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(bankPayment);
		return paymentResponse;
	}

	@Override
	public MoonReceiptVO mobilePayment(MoonPaymentVO vo) {
		MobilePayment mobilePayment = new MobilePayment(vo, new MinlengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(mobilePayment);
		return paymentResponse;
	} 
	

}
