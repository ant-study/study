package kr.co.ant.study.imsoyeon.c_oop;

import java.util.HashMap;
import java.util.Map;

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
class YPayControllerTest {
	
	@Autowired
	MockMvc mock;

	@Test
	void test(){
		try {
			Map<String, String> map = new HashMap<>();
			map.put("name", "CARD");
			map.put("cardNo", "101010101010");
			
//		mock.perform(post("/yRequestApiTest"))	→ import 필요
			mock.perform(MockMvcRequestBuilders.post("/yRequestApiTest")
					.param("productId", "P001")
					.param("productName", "컴퓨터")
					.param("amount", "1000000")
					.param("type", "CARD")
					.param("cardNo", "101010101010")
					)
//		.andExpect(status().isOK());	→ import 필요
			.andExpect(MockMvcResultMatchers.status().isOk());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
