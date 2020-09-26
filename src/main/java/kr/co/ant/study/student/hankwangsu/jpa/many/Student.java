package kr.co.ant.study.student.hankwangsu.jpa.many;

import java.util.HashSet;
import java.util.Set;

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
	private Long id;
	
	
	@ManyToMany
	@JoinTable(name = "cource_like"
		,joinColumns = @JoinColumn(name="student_id")
		,inverseJoinColumns = @JoinColumn(name="cource_id")
			)
	private Set<Cource> cources = new HashSet<Cource>();
	
	
	public void addCource(Cource c) {
		c.addStudent(this);
		cources.add(c);
	}
	
	public void removeCource(Cource c) {
		cources.remove(c);
	}
	
	
}
