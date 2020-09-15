package kr.co.ant.study.choijongmin.oop.service;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.choijongmin.oop.validate.PaymentType;
import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
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
				PropertyUtils.copyProperties(o, field.get(info));
			}
		}
		
		String jsonData = client.doPayment(mapper.writeValueAsString(o));
		
//		Object obj = info.getClass();
//		
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			System.out.println(getValue(obj, field.getName()));
//		}
//		
//		ResultPaymentInfo resultPaymentInfo = new ResultPaymentInfo();
//		resultPaymentInfo.setExtClass(clazz);
		
	}
	
}