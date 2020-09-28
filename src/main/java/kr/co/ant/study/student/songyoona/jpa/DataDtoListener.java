/**
 * Author : yooS
 * Create Date : 2020. 9. 25.
 */
package kr.co.ant.study.student.songyoona.jpa;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import kr.co.ant.study.student.songyoona.jpa.domain.YooMember;
import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 25.
 */
@Slf4j
public class DataDtoListener {
    @PostLoad
    public void postLoad(YooMember dto) {
        log.info("post load: {}", dto);
    }

    @PrePersist
    public void prePersist(YooMember dto) {
        log.info("pre persist: {}", dto);
    }

    @PostPersist
    public void postPersist(YooMember dto) {
        log.info("post persist: {}", dto);
    }

    @PreUpdate
    public void preUpdate(YooMember dto) {
        log.info("pre update: {}", dto);
    }

    @PostUpdate
    public void postUpdate(YooMember dto) {
        log.info("post update: {}", dto);
    }

    @PreRemove
    public void preRemove(YooMember dto) {
        log.info("pre remove: {}", dto);
    }

    @PostRemove
    public void postRemove(YooMember dto) {
        log.info("post remove: {}", dto);
    }
}
