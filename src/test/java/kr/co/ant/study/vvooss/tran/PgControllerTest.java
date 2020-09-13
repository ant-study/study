package kr.co.ant.study.vvooss.tran;

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
class PgControllerTest {

	@Autowired
	MockMvc mock;
	@Test
	void test() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/vvooss/test")
				.param("productId", "P0001")
				.param("productName","컴퓨터")
				.param("paymentType","CARD")
				.param("cardNo", "1111222233334444")
				.param("amount", "5000000")
				
				.param("cardCode","381") // KB_CARD
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
}
