package kr.co.ant.study.student.moonjonghun.jpa.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class MoonMember {
	
	protected MoonMember() {};

	public MoonMember(String id, String name) {
		this.id = id;
		this.name = name;
	};
	
	
	@Id
	@Column(name = "id", nullable = false, length = 100, unique = true)
	private String id;
	
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
}
