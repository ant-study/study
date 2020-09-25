/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name="y_member")
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@Getter @Setter
public class YooMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String name;
    private String addrYn;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "date", nullable=false)
    private LocalDateTime date;

    @Column(columnDefinition="decimal(25,5)") // length=20', scale='5', precision='5'scale:소수점 자릿수, precision: 소숫점을 포함한 전체 자리수
    private long stockQty;

    // length : String에만
}
