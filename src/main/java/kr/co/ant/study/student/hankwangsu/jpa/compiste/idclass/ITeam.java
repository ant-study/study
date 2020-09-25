package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key.ITeamKey;
import lombok.Getter;
import lombok.Setter;

@Entity @Table
@Getter @Setter
@IdClass(ITeamKey.class)
public class ITeam {

	/*
	 * @Id private Long companyId;
	 */
	
	@Id
	private Long teamId;
	
	@Id
	@ManyToOne
	@JoinColumn(name="company_id")
	private ICompany company;

	@OneToMany(mappedBy = "team")
	private List<IEmployee> emp = new ArrayList<>();
	
}
