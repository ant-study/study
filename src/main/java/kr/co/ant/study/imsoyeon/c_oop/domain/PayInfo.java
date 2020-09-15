package kr.co.ant.study.imsoyeon.c_oop.domain;
/*
 * product_id 
 * product_name
 * amount
 * payment_type
 * info : card_info / mobile_info / bank_account_info
 * */

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayInfo {

	private String productId;
//	private String productName;
//	private String amount;
	private String type;
	
	private T detailsInfo;
	
	public T getDetailsInfo(String type) {
		T obj = null;
		Map<String, Object> req = null;
		
		try {
		for(InfoTypeEnum classType : InfoTypeEnum.values()) {
			if (classType.equals(type)) {
//				참고해서 바꿔. 일단 casting해서 하고
//				https://stackoverflow.com/questions/27688267/how-to-make-a-generic-enum-which-could-hold-a-classt
				obj = (T) classType.getInfoClass().newInstance();
			}
		}
		
//		EnumMap ?? 모르겠는데!
		
		/*
		값 : detailsInfo.cardNo ~~
		Map에 name은 다 있구, 
		map.put("name", method.invoke("get"+"cardNo"))
		map.put("name", getCardNo())
		이렇게 할 수 없어?
		
		부모는 자식 참조 못하잖아
		
		역시 enum 하나로 하는 방법을 찾아야하지않을까?
		그럼 필요한게
		1. info Class 정보 (O)
		2. 해당 Class의 field 정보
		 * */
		
		
//		https://codereview.stackexchange.com/questions/46175/java-enum-containing-a-hash-map-for-look-up
//		for (MapEnum map : MapEnum.values()) {
//			if (map.equals(type)) {
////				req = (Map<String, Object>) map.getModel("~~");		Map 생성
//			}
//		}
		
			Iterator<Entry<String, Object>> iterator = req.entrySet().iterator();
			while(iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				
				log.info("Request Entry KEY : {}, VALYE : {}", entry.getKey(), entry.getValue());
				BeanUtils.setProperty(obj, entry.getKey(), entry.getValue());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  obj;
	}

//	이거 필요해?? 필요없어 보이는데
//	public void setDetailsInfo(String type) {
//		
//	}
	
}
