package kr.co.ant.study.jpa.basic.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="t_member")
@Slf4j @Getter @Setter
public class Member {
	
	@Id
	private String memberId;
	private String name;
	private String addrYn;
	private Integer age;
	
	@PreUpdate
	public void updateable() {
		log.info("Member Update 한다!!!");
	}
	
	@PrePersist
	public void prePersist() {
		log.info("Member prePersist 한다!!!");
	}
	

}
