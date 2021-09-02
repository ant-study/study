/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Repository
public class YooMemberRepository {

    @Autowired
    private EntityManager em;

    /* 자동생성된 해당 키값 바로 get */
    public long getGeneratedKey(final Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        return resultSet.next() ? resultSet.getLong(1) : 0L;
      }

    public <T> void save(T t) {
        em.persist(t);
    }

    public <T> void merge(T entity) {
        em.merge(entity);
    }

    public <T> void update(T t) {
        em.merge(t);
    }

    public <T> void remove(YooSaltbInit01 t) {
        em.remove(t);
    }

    public <T> YooSaltbInit01 select(T t) {
        return em.find(YooSaltbInit01.class, t);
    }
//    public void save(YooMember member) {
//        em.persist(member);
//    }
//
//    public void save(YooSaltbInit01 saltbInit) {
//        em.persist(saltbInit);
//    }
//    public void save(YooSaltbInit01Hst saltbInitHst) {
//        em.persist(saltbInitHst);
//    }

    // init01 테이블에 데이터가 들어오면 initHst 테이블에 insert가 일어난다 (trigger)

}
