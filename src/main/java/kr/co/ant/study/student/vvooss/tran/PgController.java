package kr.co.ant.study.student.vvooss.tran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.student.vvooss.tran.common.PaymentType;
import kr.co.ant.study.student.vvooss.tran.vo.PGApiVO;
import kr.co.ant.study.student.vvooss.tran.vo.ReqBodySameple;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/vvooss")
public class PgController {


	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	@ResponseBody
	@RequestMapping("/test")
	/**
	 * request -> 적절한 SettleInfo를 가진 PGVO로 컨버팅.
	 * 하나의 url로 PG Api 호출.
	 * 
	 * @param req
	 * @return
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	public String test(ReqBodySameple req) throws JsonProcessingException, Exception{
		log.info("ReqBodySample ::: {}", req.toString());
		
		//
		// req Map or plain VO for mapping Front Input value
		// return VO of paymentType
		//
		getVO(req);
		//
		// copy input value to new PGApiVO
		//
		
		// Convertor.toVO();
		
		client.doPayment(mapper.writeValueAsString(req));
		return "ok";
	}
	
	private void getVO(ReqBodySameple req) throws Exception{
		
		//
		// req의 paymentType에 해당하는 ENUM 생성. payType;
		//
		PaymentType payType = PaymentType.valueOf(req.getPaymentType().toUpperCase());
		//log.info("payType's classNm={},class={},obj={}",payType.getClassNm(),payType.getClazz().getName(),payType.getObj().toString());
		
		//
		// payType별 classNm 혹은 Class 혹은
		//
		// class 혹은 classname으로 생성한 class.getclass로 제네릭클래스의 타입매개변수로 못씀?
		// PGApiVO <clazz> pgInfo = new PGApiVO<cls>(); 
		PGApiVO<?> pgInfo = new PGApiVO<>();
		
		pgInfo.setSettleInfo(payType.getObj());
		// log.info("here=["+pgInfo.toString()+"]");
		
		
		/*
		 * 하드코딩.
		switch (payType) 
		{
			case CARD:
				PGApiVO <CardInfo> cardInfo = new PGApiVO<>();
				break;
			case MOBILE:
				PGApiVO <MobileInfo> mobileInfo = new PGApiVO<>();
				break;
			case BANK:
				PGApiVO <BankAccountInfo> bankInfo = new PGApiVO<>();
				break;
			default :
				break;
		}
		
		*/
		
	}
}
