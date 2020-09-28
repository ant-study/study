package kr.co.ant.study.jpa.basic.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {
	
	
	private String mainAddress;
	
	private String detailAddress;
	
	private String postNo;

}
