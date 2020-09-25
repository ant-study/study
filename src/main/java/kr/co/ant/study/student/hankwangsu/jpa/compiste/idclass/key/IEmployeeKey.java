package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode @ToString
public class IEmployeeKey implements Serializable{
	
	@Column(name="company_id")
	private Long companyId;
	
	@Column(name="team_id")
	private Long teamId;
	
	private Long employeeId;
	
	
}
