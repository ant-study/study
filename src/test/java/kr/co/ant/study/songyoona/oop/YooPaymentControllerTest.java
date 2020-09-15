/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.songyoona.oop;

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
//        mock.perform(MockMvcRequestBuilders.post("/yoos/test")
//                .param("productId", "P0001")
//                .param("paySubModule.name", "subsub"))
//        .andExpect(MockMvcResultMatchers.status().isOk());

        mock.perform(MockMvcRequestBuilders.post("/yoos/test")
                .param("productId", "P0002")
                .param("amount", "50000")
                .param("paymentType", "Card")
                .param("CardInfo.cardNo", "111122223333444")
                .param("CardInfo.cardCode", "001")
                .param("CardInfo.expireDate", "202411"))
        .andExpect(MockMvcResultMatchers.status().isOk());

        mock.perform(MockMvcRequestBuilders.post("/yoos/test")
                .param("productId", "P0001")
                .param("productName", "컴퓨터")
                .param("amount", "4000000")
                .param("paymentType", "Bank")
                .param("BankInfo.accountNo", "123456789012345")
                .param("BankInfo.bankCode", "003")
                .param("BankInfo.accountPw", "092021")
            )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
