package kr.co.ant.study.student.imsoyeon.d_oop.pg.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseErrorTestY {

	private String where;
	private String message;
	
	public ResponseErrorTestY(String where, String message) {
		super();
		this.where = where;
		this.message = message;
	}	
	
}
