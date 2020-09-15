package kr.co.ant.study.mkhan.oop.validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixedValidaotr implements IBaseValidator{
	
	@Override
	public boolean valid(String str, int fixedLength) {
		
		if(str == null) {
			log.debug("고정 자릿수 유효성 검사 실패. null값입니다.");
			return false;
		}
		
		if(str.length() != fixedLength) {
			log.debug("고정 자릿수 유효성 검사 실패. 16자리여야 합니다.");
			return false;
		}
		
		return true;
	}
}
