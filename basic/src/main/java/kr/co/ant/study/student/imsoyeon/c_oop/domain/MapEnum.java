package kr.co.ant.study.student.imsoyeon.c_oop.domain;

import java.util.HashMap;
import java.util.Map;

public enum MapEnum {

	data ("field", "value");
	
	private String code;
	private String description;
	
	private static Map<String, Object> model = new HashMap<String, Object>();
	
//	enum 값 가져올떄, 전체 enum 돌리는게 번거로우니까 map형태로 만드는건가봐
	static {
		for (MapEnum e : MapEnum.values()) {
			model.put(e.code, e.description);
		}
	}
	
	private MapEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Object getModel(String code) {
		return model.get(code);
	}
	
	
	/*
	private static Map<String, Object> model;
	
	private TypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Object getModel() {
		if (model == null) {
			initializeMapping();
		} 
		if (model.containsKey(code)) {
			return model.get(code);
		}
		
		return null;		
	}
	
	public void initializeMapping() {
		model = new HashMap<String, Object>();
	
		for (TypeEnum type : TypeEnum.values()) {
			model.put(type.code, type.description);
		}
	}
	 * */
}
