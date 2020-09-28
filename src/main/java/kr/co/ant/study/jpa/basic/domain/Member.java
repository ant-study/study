package kr.co.ant.study.jpa.basic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String memberId;
    
    private String name;
	private String addrYn;
	private Integer age;
	
	/*@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "mainAddress", column = @Column(name="home_address")),
		@AttributeOverride(name="detail_address", column = @Column(name="home_detail_address"))
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "mainAddress", column = @Column(name="work_address")),
		@AttributeOverride(name="detail_address", column = @Column(name="work_detail_address"))
	})
	private Address workplaceAddress;*/
	
	@PreUpdate
	public void updateable() {
		log.info("Member Update 한다!!!");
	}
	
	@PrePersist
	public void prePersist() {
		log.info("Member prePersist 한다!!!");
	}
	

}
