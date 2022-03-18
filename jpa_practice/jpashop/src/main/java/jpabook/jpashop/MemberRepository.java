package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); //저장 이후엔 아이디만 반환해서 아이디로 조회할 수 있게끔(cmd와 쿼리를 분리)
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
