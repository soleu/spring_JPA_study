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
            //비영속
//            Member member = new Member();
//            member.setId(10L);
//            member.setName("HelloJPA");
            //영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            System.out.println("=== BEFORE ===");
            em.persist(member1);
            em.persist(member2);
            System.out.println("=== AFTER ===");
            //READ
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());//1차 캐시에서 조회되어 select문 사용 X
//            System.out.println("findMember.name = " + findMember.getName());
//            //DELETE
//            em.remove(findMember);
            //UPDATE
//            findMember.setName("HelloJPA");     // persist 필요없음(update 쿼리 알아서 날림)
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

