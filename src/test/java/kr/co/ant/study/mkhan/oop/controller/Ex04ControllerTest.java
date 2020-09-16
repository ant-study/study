package kr.co.ant.study.mkhan.oop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.co.ant.study.AntStudyApplication;
@SpringBootTest(classes = { AntStudyApplication.class })
@AutoConfigureMockMvc
class Ex04ControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testCard() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/mkhan/card")
				.param("productId", "P0002")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "CARD")
				.param("BankInfo.accountNo", "22222111113333344444")
				.param("BankInfo.bankCode", "102")
				.param("BankInfo.accountPw", "1234")
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		System.out.println("111");
	}
	
//	@Test
//	void testMobile() throws Exception {
//		mock.perform(MockMvcRequestBuilders.post("/Ex04ControllerTest")
//				.param("productId", "P0001")
//				.param("productName", "컴퓨터")
//				.param("amount", "4000000")
//				.param("paymentType", "PHONE")
//				.param("BankInfo.accountNo", "01011112222")
//				.param("BankInfo.bankCode", "홍길동")
//				.param("BankInfo.accountPw", "19900120")
//				)
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	
//	@Test
//	void testBank() throws Exception {
//		mock.perform(MockMvcRequestBuilders.post("/Ex04ControllerTest")
//				.param("productId", "P0001")
//				.param("productName", "컴퓨터")
//				.param("amount", "4000000")
//				.param("paymentType", "BANK")
//				.param("BankInfo.accountNo", "2222444455556666")
//				.param("BankInfo.bankCode", "003")
//				.param("BankInfo.accountPw", "092021")
//				)
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
}
