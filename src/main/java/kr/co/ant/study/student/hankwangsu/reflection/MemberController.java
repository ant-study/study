package kr.co.ant.study.student.hankwangsu.reflection;

import java.util.HashMap;
import java.util.Map;

public class MemberController {
	
	private static Map<String, String> testData;
	private static Map<String, String> testData2;
	static {
		testData = new HashMap<String, String>();
		testData.put("A001", "한광수");
		testData.put("A002", "송정우");
		
		testData2 = new HashMap<String, String>();
		testData2.put("A001", "서울시");
		testData2.put("A002", "파주시");
	}
	
	
	public String getMemberName(SearchVO vo) {
		
		String searchId = vo.getMemberId();
		
		System.out.println("Search MemberName Input MemberID ::: "+vo.getMemberId());
		
		return testData.get(vo.getMemberId());
	}
	
	public String getMemberAddress(SearchVO vo) {
		
		String searchId = vo.getMemberId();
		
		System.out.println("Search Member Address Input MemberID ::: "+vo.getMemberId());
		
		return testData2.get(vo.getMemberId());
	}
	
	public String getMemberPhone(SearchVO vo) {
		return "phone";
	}

}
