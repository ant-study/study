package kr.co.ant.study.mkhan.oop.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinValidaotr implements IBaseValidator{
	
	public boolean valid(String str, int minLength) {
		
		if(str == null) {
			log.debug("고정 자릿수 유효성 검사 실패. null값입니다.");
			return false;
		}
		
		if(str.length() <= minLength) {
			log.debug("자릿수 유효성 검사 실패. 11자리여야 합니다.");
			return false;
		}
		
		return true;
	}

}
