package kr.co.ant.study.student.imsoyeon.c_oop.domain;

public enum InfoTypeEnum {

	CARD(CardInfo.class),
	MOBILE(MobileInfo.class),
	ACCOUNT(AccountInfo.class);
	
	private Class<?> infoClass;
	
	private InfoTypeEnum(Class<?> infoClass) {
		this.infoClass = infoClass;
	}
	
	public Class<?> getInfoClass() {
		return infoClass;
	}
}
