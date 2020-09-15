package kr.co.ant.study.oop.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.domain.BankPayInfoVO;
import kr.co.ant.study.oop.domain.CardPayInfoVO;
import kr.co.ant.study.oop.domain.MobilePayInfoVO;
import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.pg.ANTBankPaymentInfo;
import kr.co.ant.study.oop.pg.ANTCardPaymentInfo;
import kr.co.ant.study.oop.pg.ANTMobilePaymentInfo;
import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.oop.pg.BankAccountInfo;
import kr.co.ant.study.oop.pg.CardInfo;
import kr.co.ant.study.oop.pg.MobileInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {
	
	@Autowired
	private ANTPGClient client;
	
	@Autowired
	private ObjectMapper mapper;
	
	public void paymentCard(PaymentInfoVO infoVO) throws Exception{
		CardPayInfoVO vo = infoVO.getCardPayInfo();
		fixedLengthValidate(vo.getCardNo(), 16);
		log.info("카드결제 정보 ::: {}", vo);
		
		ANTCardPaymentInfo info = new ANTCardPaymentInfo();
		PropertyUtils.copyProperties(info, infoVO);
		CardInfo cardInfo = new CardInfo();
		PropertyUtils.copyProperties(cardInfo, vo);
		
		info.setCardInfo(cardInfo);
		info.setPaymentType("CARD");
		
		String json = mapper.writeValueAsString(info);
		
		client.doPayment(json);
		
	}
	
	public void paymentMobile(PaymentInfoVO infoVO) throws Exception{
		MobilePayInfoVO vo = infoVO.getMobilePayInfo();
		fixedLengthValidate(vo.getMobileNo(), 11);
		log.info("모바일 정보 ::: {}", vo);
		
		ANTMobilePaymentInfo info = new ANTMobilePaymentInfo();
		PropertyUtils.copyProperties(info, infoVO);
		
		MobileInfo mobileInfo = new MobileInfo();
		PropertyUtils.copyProperties(mobileInfo, vo);
		
		info.setMobileInfo(mobileInfo);
		info.setPaymentType("MOBILE");
		
		String json = mapper.writeValueAsString(info);
		
		client.doPayment(json);
		
	}
	
	public void paymentBank(PaymentInfoVO infoVO) throws Exception{
		BankPayInfoVO vo = infoVO.getBankPayInfo();
		//Validate 체크
		minLengthValidate(vo.getAccountNo(), 11);
		
		//logging
		log.info("은행 정보 ::: {}", vo);
		
		ANTBankPaymentInfo info = new ANTBankPaymentInfo();
		PropertyUtils.copyProperties(info, infoVO);
		
		BankAccountInfo bankAccountInfo = new BankAccountInfo();
		PropertyUtils.copyProperties(bankAccountInfo, vo);
		
		info.setBankAccountInfo(bankAccountInfo);
		info.setPaymentType("BANK");
		
		String json = mapper.writeValueAsString(info);
		
		client.doPayment(json);
		
	}
	
	
	private void fixedLengthValidate(String s, int fixedLength) throws ValidateException {
		if(s.length() != fixedLength) {
			throw new ValidateException(fixedLength+ "자리가 아닙니다.");
		}
	}
	
	private void minLengthValidate(String s, int minLength) throws ValidateException {
		if(s.length() < minLength) {
			throw new ValidateException(minLength+ "자리보다 커야 합니다.");
		}
	}

}
