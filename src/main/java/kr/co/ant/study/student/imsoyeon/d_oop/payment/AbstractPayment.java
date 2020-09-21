package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public abstract class AbstractPayment implements Payment {
	
	private RequestPayInfo inputVO;
	private PGValidatorY validator;
	private BIPredicateValidatorY bpValidator;
	
	@Autowired
	ObjectMapper mapper;

	public AbstractPayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super();
		this.inputVO = inputVO;
		this.validator = validator;
	}
	
	public AbstractPayment(RequestPayInfo inputVO, BIPredicateValidatorY bpValidator) {
		super();
		this.inputVO = inputVO;
		this.bpValidator = bpValidator;
	}

	@Override
	public void beforeAPI() throws Exception {
				
	}	

	@Override
	public void logging() throws Exception {
		log.info("Request VO : {}", inputVO);
	}
	
	@Override
	public PGPaymentInfo convertToPaymentVO() throws Exception {
		PGPaymentInfo paymentVO = new PGPaymentInfo();
		PropertyUtils.copyProperties(paymentVO, inputVO);
		return paymentVO;
	}

	@Override
	public void requestPGAPI(PGPaymentInfo payment) throws Exception {
//		String json = mapper.writeValueAsString(payment);
//		ANTPGClientY client = new ANTPGClientY();
//		client.doPayment(json);
	}
	
}
