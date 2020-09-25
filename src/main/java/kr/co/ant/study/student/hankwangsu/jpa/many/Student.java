package kr.co.ant.study.student.hankwangsu.jpa.many;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Student {
	
	@Id	@GeneratedValue
	@Column(length = 255, columnDefinition = "VARCHAR")
	private UUID id;
	
	
	@ManyToMany
	@JoinTable(name = "cource_like"
		,joinColumns = @JoinColumn(name="student_id")
		,inverseJoinColumns = @JoinColumn(name="cource_id")
			)
	private List<Cource> cources = new ArrayList<Cource>();
	
	
	public void addCource(Cource c) {
		cources.add(c);
	}
}
