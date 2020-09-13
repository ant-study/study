package kr.co.ant.study.vvooss.tran.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleInfo {
	
	// 전송이력 dao 혹은 서비스 멤버를 가진다.
	
	
	//
	// 아무것도없으면 심심하자나,,
	// 결제구분별 클래스이름을 저장한다.
	//
	private String fieldName;
	
	//
	// 전송할 url을 가지고 있다.
	//
	private String postUrl;
	
	public SettleInfo() {};
	
	public SettleInfo(String s) {
		this.fieldName = s;
	}
	
	/**
	 * 전송이력을 생성한다.
	 * @param <T>
	 * @param t
	 */
	public <T extends SettleInfo> void saveTranHistory(T t) {
		System.out.println("전송이력생성합니다. [ "+ t.toString()+"]");
	}
	
}
