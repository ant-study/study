package kr.co.ant.study.student.moonjonghun.jpa.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class MoonMember {
	
	protected MoonMember() {};

	@Builder
	public MoonMember(String id, String name) {
		this.id = id;
		this.name = name;
	};
	
	@Column(name = "index", nullable = false, length = 1000, unique = true, columnDefinition = "DECIMAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int index;
	
	@Id
	@Column(name = "id", nullable = false, length = 100, unique = true)
	private String id;
	
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
}
