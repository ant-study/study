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

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Cource {

	
	@Id @GeneratedValue
	@Column(length = 255, columnDefinition = "VARCHAR")
	private UUID id;
	
	@ManyToMany
	@JoinTable(name = "cource_like"
	,joinColumns = @JoinColumn(name="cource_id")
	,inverseJoinColumns = @JoinColumn(name="student_id")
		)
	private List<Student> students = new ArrayList<Student>();
	
	public void addStudent(Student s) {
		students.add(s);
	}
}
