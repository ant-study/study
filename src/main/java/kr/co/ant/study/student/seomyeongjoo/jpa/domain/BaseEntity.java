package kr.co.ant.study.student.seomyeongjoo.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    private String tenantId;

    private String enplcCd;

    private String sysRegId;

    private LocalDateTime sysRegDate = LocalDateTime.now();

    private String sysUpdId;

    private LocalDateTime sysUpdDate;

//    public BaseEntity(String tenantId, String enplcCd, String sysRegId
//                , LocalDateTime sysRegDate, String sysUpdId, LocalDateTime sysUpdDate) {
//        this.tenantId = tenantId;
//        this.enplcCd = enplcCd;
//        this.sysRegId = sysRegId;
//        this.sysRegDate = sysRegDate;
//        this.sysUpdId = sysUpdId;
//        this.sysUpdDate = sysUpdDate;
//    }
}
