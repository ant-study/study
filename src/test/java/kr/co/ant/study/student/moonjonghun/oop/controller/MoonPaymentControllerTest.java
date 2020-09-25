package kr.co.ant.study.student.moonjonghun.oop.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.co.ant.study.AntStudyApplication;
import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = {AntStudyApplication.class })
@AutoConfigureMockMvc
class MoonPaymentControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testGetPayInfoByCard() throws Exception{
//		String val = MoonPaymentType.Card.getValue();
		mock.perform(
			MockMvcRequestBuilders.post("/moon/card")
			.param("productId", "P0001")
			.param("productName", "컴퓨터")
			.param("amount", "10")
			.param("paymentType", MoonPaymentType.Card.getValue())
			.param("cardPayInfo.cardNo", "2222444455556666")
			.param("cardPayInfo.cardCode", "003")
			.param("cardPayInfo.expireDate", "092021")
		).andExpect(MockMvcResultMatchers.status().isOk());
		log.info("getPayInfoByCard Method Test");
		
//		fail("Not yet implemented");
	}

	@Test
	void testGetPayInfoByMobile() throws Exception{
		mock.perform(
			MockMvcRequestBuilders.post("/moon/mobile")
			.param("productId", "P0002")
			.param("productName", "핸드폰")
			.param("amount", "20")
			.param("paymentType", MoonPaymentType.Mobile.getValue())
			.param("mobilePayInfo.mobileNo", "01011112222")
			.param("mobilePayInfo.userName", "홍길동")
			.param("mobilePayInfo.birthday","19900120")
		).andExpect(MockMvcResultMatchers.status().isOk());
		fail("Not yet implemented");
	}

	@Test
	void testGetPayInfoByBankAccount() throws Exception{
		mock.perform(
				MockMvcRequestBuilders.post("/moon/bank")
				.param("productId", "P0001")
				.param("productName", "부품")
				.param("amount", "30")
				.param("paymentType", MoonPaymentType.Bank.getValue())
				.param("accountPayInfo.accountNo", "22222111113333344444")
				.param("accountPayInfo.bankCode", "102")
				.param("accountPayInfo.accountPw", "1234")
			).andExpect(MockMvcResultMatchers.status().isOk());
		fail("Not yet implemented");
	}

}
