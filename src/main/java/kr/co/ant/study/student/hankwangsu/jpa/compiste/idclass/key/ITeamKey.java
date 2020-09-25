package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter @Setter
@EqualsAndHashCode @ToString
public class ITeamKey implements Serializable{

	private Long teamId;
	
	private Long companyId;
}
