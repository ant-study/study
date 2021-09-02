package kr.co.ant.study.student.hankwangsu.oop.payment;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AnswerPaymentFactory{
	Map<AnswerPaymentType, Class<? extends Payment>> map;
	
	
	

}
