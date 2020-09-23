package kr.co.ant.study.student.moonjonghun.stream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonToMapTest {

	private int idx;
	private String data;
	
	public MoonToMapTest(int idx, String data) {
		this.idx = idx;
		this.data = data;
	}
 
}
