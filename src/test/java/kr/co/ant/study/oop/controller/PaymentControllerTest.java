package kr.co.ant.study.oop.controller;

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
class PaymentControllerTest {

	@Autowired
	MockMvc mock;
	
	@Test
	void testTest() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/test")
				.param("productId", "P0001")
				.param("paySubModule.name", "subsub"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
