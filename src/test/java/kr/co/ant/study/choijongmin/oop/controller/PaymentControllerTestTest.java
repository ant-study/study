package kr.co.ant.study.choijongmin.oop.controller;

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
class PaymentControllerTestTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testCard() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/paymentControllerTest")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "CARD")
				.param("cardInfo.cardNo", "2222444455556666")
				.param("cardInfo.cardCode", "003")
				.param("cardInfo.expireDate", "092021")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testBank() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/paymentControllerTest")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "Bank")
				.param("bankAccountInfo.accountNo", "2222444455556666")
				.param("bankAccountInfo.bankCode", "003")
				.param("bankAccountInfo.accountPw", "092021")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testMobile() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/paymentControllerTest")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "Mobile")
				.param("mobileInfo.mobileNo", "01011112222")
				.param("mobileInfo.userName", "홍길동")
				.param("mobileInfo.birthday", "19900120")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}

