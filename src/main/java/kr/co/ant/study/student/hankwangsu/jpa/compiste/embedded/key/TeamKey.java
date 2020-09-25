package kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter
@EqualsAndHashCode @ToString
public class TeamKey implements Serializable{

	@Column(name="t_team_id")
	private Long teamId;
	
	@Column(name="t_company_id")
	private Long companyId;
}
