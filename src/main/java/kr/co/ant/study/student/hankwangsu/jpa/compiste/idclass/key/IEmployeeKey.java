package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Embeddable
@EqualsAndHashCode @ToString
public class IEmployeeKey implements Serializable{
	
	@Id
	private Long companyId;
	
	@Id
	private Long teamId;
	
	@Id
	private Long employeeId;
	
	
}
