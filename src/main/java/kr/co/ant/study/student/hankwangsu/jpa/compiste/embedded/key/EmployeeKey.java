package kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Embeddable
@EqualsAndHashCode @ToString
public class EmployeeKey implements Serializable{
	
	//Employee Entity의 Team 연관관계에서 Team의 Id가 mapsId로 지정한 속성명으로 맵핑된다.
	private TeamKey teamKey;
	
	private Long employeeId;
	
	public EmployeeKey() {}
	
	//Employee Entity의 Team 연관관계에서 mapsId
	public EmployeeKey(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
