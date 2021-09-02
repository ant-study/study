package kr.co.ant.study.student.hankwangsu.jpa.many;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Cource {

	
	@Id @GeneratedValue
	@Column(length = 255, columnDefinition = "VARCHAR")
	private UUID id;
	
	@ManyToMany(mappedBy = "cources")
	private Set<Student> students = new HashSet<Student>();
	
	public void addStudent(Student s) {
		students.add(s);
	}
	
	public void removeStudent(Student s) {
		students.remove(s);
	}
}
