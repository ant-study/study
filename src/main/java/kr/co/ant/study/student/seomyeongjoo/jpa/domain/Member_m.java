package kr.co.ant.study.student.seomyeongjoo.jpa.domain;

import javax.persistence.*;

@Entity
@Table(name="member")
public class Member_m {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String userName;
    private int age;


    public Member_m(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }


}
