/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Entity
@Table(name="t_member")
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@Getter
@Setter
public class YooMember {

    @Id
    private String memberId;
    private String name;
    private String addrYn;
    @DateTimeFormat
    private String date;
}
