package kr.co.ant.study.student.choijongmin.oop.service;

import java.lang.reflect.Field;

import kr.co.ant.study.student.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.student.choijongmin.oop.validate.PaymentType;
import kr.co.ant.study.student.choijongmin.oop.vo.PgPaymentInfo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.student.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.oop.pg.ANTPGClient;

@Service
public class PaymentServiceTest {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	public void paymentTypeClassification(PaymentInfo info) throws Exception {
		PgPaymentInfo pgPaymentInfo = new PgPaymentInfo();
		PropertyUtils.copyProperties(pgPaymentInfo, info);
		
		Class clazz = PaymentType.valueOf(info.getPaymentType()).getPaymentClass();
		Object o = clazz.newInstance();
		
		Field[] fields = info.getClass().getDeclaredFields();
		for (Field field : fields) {
			if ( clazz.isAssignableFrom(field.getType()) ) {
				Object fObj = field.get(info);
				pgPaymentInfo.setExtClass(fObj);
			}
		}
		
		PaymentStrategy paymentStrategy = null;
		paymentStrategy = (PaymentStrategy) pgPaymentInfo.getExtClass();
		
		PaymentFacadeTest paymentFacadeTest = new PaymentFacadeTest();
		paymentFacadeTest.doPayment(paymentStrategy, pgPaymentInfo);
		
	}
	
}