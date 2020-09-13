/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.songyoona.oop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import kr.co.ant.study.AntStudyApplication;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 11.
 */
@SpringBootTest(classes = { AntStudyApplication.class })
@AutoConfigureMockMvc
class YooPaymentControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    void testTest() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/yoos/test")
                .param("productId", "P0001")
                .param("paySubModule.name", "subsub"))
        .andExpect(MockMvcResultMatchers.status().isOk());

        mock.perform(MockMvcRequestBuilders.post("/yoos/test")
                .param("productId", "P0002")
                .param("paySubModule.name", "BANK"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
