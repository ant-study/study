/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2;

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
 * @createDate : 2020. 9. 16.
 */
@SpringBootTest(classes = { AntStudyApplication.class })
@AutoConfigureMockMvc
class YooPaymentPControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    void testCardPayTest() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/yoos/pay/card")
                .param("productId", "P0001")
                .param("productName", "컴퓨터")
                .param("amount", "500000")
                .param("paymentType", "CARD")
                .param("cardPayInfo.cardNo", "1111222233334444")
                .param("cardPayInfo.expireDate", "092021")
                .param("cardPayInfo.cardCode", "312"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testMobilePayTest() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/yoos/pay/mobile")
                .param("productId", "P0001")
                .param("productName", "컴퓨터")
                .param("amount", "400000")
                .param("paymentType", "MOBILE")
                .param("mobilePayInfo.mobileNo", "01033334444")
                .param("mobilePayInfo.userName", "홍길동")
                .param("mobilePayInfo.brithDay", "19900210"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testBankPayTest() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/yoos/pay/bank")
                .param("productId", "P0001")
                .param("productName", "컴퓨터")
                .param("amount", "300000")
                .param("paymentType", "BANK")
                .param("bankPayInfo.accountNo", "111122223333444455556666")
                .param("bankPayInfo.bankCode", "120")
                .param("bankPayInfo.accountPw", "1224"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
