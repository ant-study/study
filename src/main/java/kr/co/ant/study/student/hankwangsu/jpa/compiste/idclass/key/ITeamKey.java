package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode @ToString
public class ITeamKey implements Serializable{

	private Long company;
//	@Column(name="team_id")
	private Long teamId;
	
}
