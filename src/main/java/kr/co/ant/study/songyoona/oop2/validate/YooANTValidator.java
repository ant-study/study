package kr.co.ant.study.songyoona.oop2.validate;

import kr.co.ant.study.songyoona.oop2.service.ValidateException;

public interface YooANTValidator{
	public boolean validate(int length, int target) throws ValidateException;
}
