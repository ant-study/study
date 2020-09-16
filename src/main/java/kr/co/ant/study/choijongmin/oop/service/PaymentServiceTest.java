package kr.co.ant.study.choijongmin.oop.service;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.choijongmin.oop.validate.PaymentType;
import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.choijongmin.oop.vo.PgPaymentInfo;
import kr.co.ant.study.oop.pg.ANTPGClient;

@Service
public class PaymentServiceTest {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	public void paymentTypeClassification(PaymentInfo info) throws Exception {
		
		Class clazz = PaymentType.valueOf(info.getPaymentType()).getPaymentClass();
		Object o = clazz.newInstance();
		
		Field[] fields = info.getClass().getDeclaredFields();
		for (Field field : fields) {
			if ( clazz == field.getType() ) {
				Object fObj = field.get(info);				
				PropertyUtils.copyProperties(o, fObj);
			}
		}
		
		PaymentStrategy paymentStrategy = null;
		paymentStrategy = (PaymentStrategy) o;
		
		PaymentFacadeTest paymentFacadeTest = new PaymentFacadeTest();
		paymentFacadeTest.doPayment(paymentStrategy, info);
		
	}
	
}