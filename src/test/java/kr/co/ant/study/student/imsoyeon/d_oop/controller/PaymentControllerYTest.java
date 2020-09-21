package kr.co.ant.study.student.imsoyeon.d_oop.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.co.ant.study.AntStudyApplication;

@SpringBootTest(classes = {AntStudyApplication.class})
@AutoConfigureMockMvc
class PaymentControllerYTest {
	
	@Autowired
	MockMvc mock;

	@Test
	void testPayCard() {
//		mock.perform(post("/yRequestApiTest"))	→ import 필요
		try {
			mock.perform(MockMvcRequestBuilders.post("/payCard")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "CARD")
					.param("cardInfo.cardNo", "1010101010100045")
					.param("cardInfo.cardCode", "008")
					.param("cardInfo.expireDate", "210915")				
					)
//		.andExpect(status().isOK());	→ import 필요
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPayAccount(){
		try {
			mock.perform(MockMvcRequestBuilders.post("/payAccount")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "ACCOUNT")
					.param("accountInfo.accountNo", "454845456597455")
					.param("accountInfo.bankCode", "777")
					.param("accountInfo.accountPw", "1234")				
					)
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPayMobile(){
		try {
			mock.perform(MockMvcRequestBuilders.post("/payMobile")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "MOBILE")
					.param("mobileInfo.mobileNo", "01012121545")
					.param("mobileInfo.userName", "아무개")
					.param("mobileInfo.birthday", "19001225")					
					)
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
