package kr.co.ant.study.seomyeongjoo.oop.controller;

import kr.co.ant.study.AntStudyApplication;
import kr.co.ant.study.student.seomyeongjoo.oop.controller.JuuPaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = { AntStudyApplication.class })
@AutoConfigureMockMvc
class JuuPaymentControllerTest {

    @Autowired
    MockMvc mock;

    @Autowired
    JuuPaymentController paymentController;


    @Test
    void testTest() throws Exception {

        mock.perform(MockMvcRequestBuilders.post("/mj/card")
                .param("productId", "P0001")
                .param("paySubModule.name", "CARD")
                .param("cardInfo.cardNo", "22224444555566666")
                .param("cardInfo.cardCode", "003")
                .param("cardInfo.expireDate", "092021")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}