package kr.co.ant.study.imsoyeon.c_oop.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonInfo <T extends DetailsInfo> {
	
	private String type;
	
	private String productId;
	private String productName;
	private String amount;
	
	private RequestPaySample req;
	private T detailsInfo;
	private CardInfo cardInfo;
	private AccountInfo accountInfo;
	private MobileInfo mobileInfo;
	
	public void setDetailsInfo(String type, RequestPaySample req) {
		T obj = null;
		try {
			
			for(InfoTypeEnum classType : InfoTypeEnum.values()) {
				if (classType.name().equals(type)) {
//					참고해서 바꿔. 일단 casting해서 하고
//					https://stackoverflow.com/questions/27688267/how-to-make-a-generic-enum-which-could-hold-a-classt
					obj = (T) classType.getInfoClass().newInstance();
				}
			}
			
			PropertyUtils.copyProperties(obj, req);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.detailsInfo = obj;
	}
}
