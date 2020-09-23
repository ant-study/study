package kr.co.ant.study.student.seomyeongjoo.jpa.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="member")
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String userName;
    private int age;


    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
