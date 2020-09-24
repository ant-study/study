package kr.co.ant.study.student.imsoyeon.g_jpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity(name = "InitHstEntity")
//@Table(name = "saltb_init01_hst_y")
//@DynamicUpdate	//	value가 변경된 컬럼만 update쳐라
@Getter
@Setter
@ToString
public class InitHstEntityY {

	private int 	initHstId;
	private int 	initId;
	private int 	seq;
	private String 	eventDscd;
	private String 	tenantId;
	private String 	enplcCd;
	private String 	storeCd;
	private String 	itemCd;
	private int 	stockQty;
	private int 	stockAmt;
	private String 	sysRegId;
	private Date 	sysRegDate;
	private String 	sysUpdId;
	private Date 	sysUpdDate;
}
