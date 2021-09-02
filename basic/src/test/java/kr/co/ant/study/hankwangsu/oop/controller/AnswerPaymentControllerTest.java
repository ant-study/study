package kr.co.ant.study.hankwangsu.oop.controller;

import static org.junit.jupiter.api.Assertions.*;

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
class AnswerPaymentControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testCardPayTest() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/hks2/pay/card")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "400000")
				.param("cardPayInfo.cardNo", "1111222233334444")
				.param("cardPayInfo.expireDate", "092021")
				.param("cardPayInfo.cardCode", "312"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testMobilePayTest() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/hks2/pay/mobile")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "400000")
				.param("mobilePayInfo.mobileNo", "01033334444")
				.param("mobilePayInfo.userName", "홍길동")
				.param("mobilePayInfo.brithDay", "19900210"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testBankPayTest() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/hks2/pay/bank")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "400000")
				.param("bankPayInfo.accountNo", "111122223333444455556666")
				.param("bankPayInfo.bankCode", "120")
				.param("bankPayInfo.accountPw", "1224"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
