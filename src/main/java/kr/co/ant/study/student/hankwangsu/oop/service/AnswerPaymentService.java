package kr.co.ant.study.student.hankwangsu.oop.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.domain.BankPayInfoVO;
import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.pg.ANTBankPaymentInfo;
import kr.co.ant.study.oop.service.ValidateException;
import kr.co.ant.study.student.hankwangsu.oop.payment.AnswerCardPayment;
import kr.co.ant.study.student.hankwangsu.oop.payment.AnswerMobilePayment;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.ANTPaymentResponse;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.BankAccountInfo;
import kr.co.ant.study.student.hankwangsu.oop.validate.FixedLengthValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerPaymentService {
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	AnswerPaymentFacade facade;
	
	public void paymentCard(PaymentInfoVO infoVO) throws Exception{
		
		AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, new FixedLengthValidator());
		
		ANTPaymentResponse response = facade.doPayment(cardPayment);
		
	}
	
	public void paymentMobile(PaymentInfoVO infoVO) throws Exception{
		
		AnswerMobilePayment mobilePayment = new AnswerMobilePayment(infoVO, new FixedLengthValidator());
		
		ANTPaymentResponse response = facade.doPayment(mobilePayment);
		
		
	}
	
	public void paymentBank(PaymentInfoVO infoVO) throws Exception{
		
		
	}
	
	
	/**
	 * 한 메소드에서 PaymentType에 따라 여러가지 결제수단 처리
	 * 아래 로직을 Factory Pattern을 참고 해서
	 * Factory Class를 만듭시다.
	 * @param infoVO
	 * @throws Exception
	 */
	public void compositePayment(PaymentInfoVO infoVO)throws Exception {
		
		String paymentType = infoVO.getPaymentType();
		
		if("CARD".equals(paymentType)) {
			AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, new FixedLengthValidator());
			
			ANTPaymentResponse response = facade.doPayment(cardPayment);
		}else if("MOBILE".equals(paymentType)) {
			AnswerMobilePayment mobilePayment = new AnswerMobilePayment(infoVO, new FixedLengthValidator());
			
			ANTPaymentResponse response = facade.doPayment(mobilePayment);
		}else if("BANK".equals(paymentType)) {
			//은행 ~~
		}
	}
	

}
