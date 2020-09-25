package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Table
@Getter @Setter
public class ICompany {

	@Id	@GeneratedValue
	private Long companyId;
	
}
