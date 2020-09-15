package kr.co.ant.study.imsoyeon.c_oop.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.imsoyeon.c_oop.domain.CommonInfo;
import kr.co.ant.study.imsoyeon.c_oop.domain.DetailsInfo;
import kr.co.ant.study.imsoyeon.c_oop.domain.RequestPaySample;
import kr.co.ant.study.oop.pg.ANTPGClient;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class YPayController1 {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;

	@RequestMapping(value = "/yRequestApiTest")
	public String requestApi(RequestPaySample request) throws Exception {
		log.info("PaymentInfo ::: {}", request);
		
		CommonInfo<DetailsInfo> vo = new CommonInfo<DetailsInfo>();
		PropertyUtils.copyProperties(vo, request);
		vo.setDetailsInfo(request.getType(), request);
		
		client.doPayment(mapper.writeValueAsString(vo));
		return "ok";
	}
}
