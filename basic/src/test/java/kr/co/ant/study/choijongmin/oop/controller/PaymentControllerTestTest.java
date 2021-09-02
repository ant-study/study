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
				.param("InCardInfo.cardNo", "2222444455556666")
				.param("InCardInfo.cardCode", "003")
				.param("InCardInfo.expireDate", "092021")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testBank() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/paymentControllerTest")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "BANK")
				.param("InBankAccountInfo.accountNo", "2222444455556666")
				.param("InBankAccountInfo.bankCode", "003")
				.param("InBankAccountInfo.accountPw", "092021")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testMobile() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/paymentControllerTest")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "MOBILE")
				.param("InMobileInfo.mobileNo", "0101111222")
				.param("InMobileInfo.userName", "홍길동")
				.param("InMobileInfo.birthday", "19900120")
			)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}

