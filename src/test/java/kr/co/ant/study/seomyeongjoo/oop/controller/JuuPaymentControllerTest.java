package kr.co.ant.study.seomyeongjoo.oop.controller;

import kr.co.ant.study.AntStudyApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = { AntStudyApplication.class })
@AutoConfigureMockMvc
class JuuPaymentControllerTest {

    @Autowired
    MockMvc mock;

    @Autowired
    JuuPaymentController paymentController;


    @Test
    void testTest() throws Exception {

        mock.perform(MockMvcRequestBuilders.post("/testMJ")
                .param("productId", "P0001")
                .param("paySubModule.name", "CARD"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}