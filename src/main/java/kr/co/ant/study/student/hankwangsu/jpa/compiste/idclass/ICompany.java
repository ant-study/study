package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Team;
import lombok.Getter;
import lombok.Setter;

@Entity @Table
@Getter @Setter
public class ICompany {

	@Id	@GeneratedValue
	private Long companyId;
	
	@OneToMany(mappedBy = "company")
	private List<ITeam> teams = new ArrayList<>();
	
	public void addTeam(ITeam team) {
		teams.add(team);
		team.setCompany(this);
	}
	
}
