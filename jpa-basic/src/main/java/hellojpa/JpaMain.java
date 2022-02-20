package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        //emf,em 생성
        //emf는 딱 한번, em은 트랜젝션 단위
        //jpa는 모든 작업을 트랜젝션 단위 안에서 작업해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code 작성
        try {
            //CREATE
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);
            //READ
            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());
//            //DELETE
//            em.remove(findMember);
            //UPDATE
            findMember.setName("HelloJPA");     // persist 필요없음(update 쿼리 알아서 날림)
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        //끝내기
        emf.close();
    }
}

