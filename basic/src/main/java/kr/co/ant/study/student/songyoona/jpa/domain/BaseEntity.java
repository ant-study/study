package kr.co.ant.study.student.songyoona.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    @Column(name = "tenant_id", nullable = false, length = 27)
    public String tenantId;

    @Column(name = "enplc_cd", nullable = false, length = 4)
    public String enplcCd;

    @Column(name = "sys_reg_id", nullable = false, length = 27)
    public String sysRegId;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "sys_reg_date", nullable = false) // , columnDefinition="default CURRENT_TIMESTAMP"
    public LocalDateTime sysRegDate = LocalDateTime.now();

    @Column(name = "sys_upd_id", length = 27)
    public String sysUpdId;

    @Column(name = "sys_upd_date")
    public LocalDateTime sysUpdDate;


}
