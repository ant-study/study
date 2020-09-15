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
	
	private T detailsInfo;
	
	private CardInfo cardInfo;
	private AccountInfo accountInfo;
	private MobileInfo mobileInfo;
	
	public void setDetailsInfo(String type, RequestPaySample req) {
		Object obj = null;
		try {
			
			for(InfoTypeEnum classType : InfoTypeEnum.values()) {
				if (classType.name().equals(type)) {
//					참고해서 바꿔. 일단 casting해서 하고
//					https://stackoverflow.com/questions/27688267/how-to-make-a-generic-enum-which-could-hold-a-classt
					obj = classType.getInfoClass().newInstance();
				}
			}
			
			PropertyUtils.copyProperties(obj, req);
//		this.detailsInfo = obj;
			
//		근데 switch로 분기해서해야해?
		switch (type) {
		case "CARD":
			this.cardInfo = (CardInfo) obj;
			break;

		case "ACCOUNT":
			this.accountInfo = (AccountInfo) obj;
			break;
		
		case "MOBILE":
			this.mobileInfo = (MobileInfo) obj;
			break;
			
		default:
			break;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
