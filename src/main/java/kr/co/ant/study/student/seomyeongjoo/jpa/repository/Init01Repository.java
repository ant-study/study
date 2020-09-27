package kr.co.ant.study.student.seomyeongjoo.jpa.repository;

import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Init01;
import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Init01History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class Init01Repository {

    @Autowired
    private EntityManager em;

    public void save(Init01 init01){
        em.persist(init01);
    };

    public Init01 find(Long idx){
        Init01 init01 = em.find(Init01.class, idx);
        return init01;
    }

    public void delete(Init01 init01){
        //부모는 삭제되지만 -> 자식은 저장
        //부모와 자식의 연관관계를 끊어야
        List<Init01History> hsts = init01.getInit01HistoryList();
        for (Init01History hst : hsts) {
            String status = hst.getEventDscd();
            System.out.println("-------------------------------");
            System.out.println("--------------" + status + "--------------");
            em.persist(hst);
            //em.flush();
            System.out.println("-------------------------------");
        }
        init01.setInit01HistoryList(null);
        em.remove(init01);

    }
//    public List<Init01> findAll(){
//        List<Init01> init01List = em.find()
//    }
}

