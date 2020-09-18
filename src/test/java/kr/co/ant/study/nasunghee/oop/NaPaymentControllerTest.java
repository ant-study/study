package kr.co.ant.study.nasunghee.oop;

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
class NaPaymentControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testTest() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/sh/test")
				.param("productId", "P0001")
				.param("productName", "컴퓨터")
				.param("amount", "4000000")
				.param("paymentType", "CARD")
				.param("cardInfo.cardNo", "2222444455556666"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
