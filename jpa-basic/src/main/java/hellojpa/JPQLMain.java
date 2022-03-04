package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPQLMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            
            //Criteria 사용 준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            //동적 쿼리 사용 예시
            String username = "gdgd";
            if (username != null) {
                cq.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();

//            //JPQL , 엔티티 객체를 기준
//            List<Member> result=em.createQuery("select m From Member m where m.username like '%kim%'", Member.class).getResultList();
//for (Member member:result){
//    System.out.println("member = "+member);
//        }
        } catch (
                Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
