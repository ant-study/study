package kr.co.ant.study.student.hankwangsu.generic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_member")
@Getter
@Setter
public class Member {
	
	@Id
	private String memberId;
	private String name;
	private String addrYn;
	private Integer age;

}
