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
		//Validate 체크
		fixedLengthValidate(vo.getCardNo(), 16);
		//Logging
		log.info("카드결제 정보 ::: {}", vo);
		
		//PG사로 보낼 VO 생성
		ANTCardPaymentInfo info = new ANTCardPaymentInfo();
		//화면에서 넘어온 값을 PG사로 보낼 VO에 값 복사
		PropertyUtils.copyProperties(info, infoVO);
		
		//PG사로 보낼 카드정보
		CardInfo cardInfo = new CardInfo();
		
		//PG사로 보낼 값 복사
		PropertyUtils.copyProperties(cardInfo, vo);
		
		//카드정보 셋팅 및 결제타입 정의
		info.setCardInfo(cardInfo);
		info.setPaymentType("CARD");
		
		//Json 변환
		String json = mapper.writeValueAsString(info);
		
		//결제 요청
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
