package kr.co.ant.study.moonjonghun.oop.validation;

import kr.co.ant.study.moonjonghun.oop.domain.MoonAccountInfo;
import kr.co.ant.study.moonjonghun.oop.domain.MoonCardInfo;
import kr.co.ant.study.moonjonghun.oop.domain.MoonMobileInfo;

public interface validatePayment {

	public void validate(MoonCardInfo mc) throws Exception;
	
	public void validate(MoonMobileInfo mm) throws Exception;
	
	public void validate(MoonAccountInfo mb) throws Exception;
}
