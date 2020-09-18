package kr.co.ant.study.student.imsoyeon.c_oop.controller;

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
class YPayController1Test {
	
	@Autowired
	MockMvc mock;

	@Test
	void testPayCard(){
		try {
//		mock.perform(post("/yRequestApiTest"))	→ import 필요
			mock.perform(MockMvcRequestBuilders.post("/yRequestApiTest")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "CARD")
					.param("cardNo", "101010101010")
					.param("cardCode", "008")
					.param("expireDate", "210915")				
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
			mock.perform(MockMvcRequestBuilders.post("/yRequestApiTest")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "ACCOUNT")
					.param("accountNo", "454845456597455")
					.param("bankCode", "777")
					.param("accountPw", "1234")				
					)
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPayMobile(){
		try {
			mock.perform(MockMvcRequestBuilders.post("/yRequestApiTest")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "MOBILE")
					.param("mobileNo", "01012121545")
					.param("userName", "아무개")
					.param("birthday", "19001225")					
					)
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
