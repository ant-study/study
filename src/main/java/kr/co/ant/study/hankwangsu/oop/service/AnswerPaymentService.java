package kr.co.ant.study.hankwangsu.oop.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.hankwangsu.oop.payment.AnswerCardPayment;
import kr.co.ant.study.hankwangsu.oop.payment.AnswerMobilePayment;
import kr.co.ant.study.hankwangsu.oop.pg.vo.ANTPaymentResponse;
import kr.co.ant.study.hankwangsu.oop.pg.vo.BankAccountInfo;
import kr.co.ant.study.hankwangsu.oop.validate.FixedLengthValidator;
import kr.co.ant.study.oop.domain.BankPayInfoVO;
import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.pg.ANTBankPaymentInfo;
import kr.co.ant.study.oop.service.ValidateException;
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
	
	

}
