package kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table
@Getter @Setter @ToString
public class Company {

	@Id	@GeneratedValue
	private Long companyId;
	
	
}
