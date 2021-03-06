/**
 * Author : jmChoi
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.choijongmin.oop.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.student.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.student.choijongmin.oop.vo.PgPaymentInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @description : 
 * @author : jmChoi
 * @createDate : 2020. 9. 16.
 */
@Slf4j
public abstract class PaymentAbs implements PaymentStrategy{
	
	@Override
	public PgPaymentInfo paymentConvert(PaymentInfo info) {
		PgPaymentInfo pgPaymentInfo = new PgPaymentInfo();
		try {
			PropertyUtils.copyProperties(pgPaymentInfo, info);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pgPaymentInfo;
	}
}

