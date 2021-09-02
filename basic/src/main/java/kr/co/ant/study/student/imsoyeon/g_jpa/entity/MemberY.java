package kr.co.ant.study.student.imsoyeon.g_jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member_y")
@DynamicUpdate	//	value가 변경된 컬럼만 update쳐라
@Getter
@Setter
@ToString
public class MemberY {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long entityId;
	private String memberId;
	private String name;
}
