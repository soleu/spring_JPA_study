package jpql;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
Member member =new Member();
member.setUsername("member1");
member.setAge(10);
em.persist(member);
            TypedQuery<Member> query = em.createQuery("select m from Member m",Member.class);
            TypedQuery<String> query1 = em.createQuery("select m.username from Member m",String.class);
            Query query2= em.createQuery("select m.username,m.age from Member m"); //타입을 특정 지을 수 없을때는 query 사용
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
