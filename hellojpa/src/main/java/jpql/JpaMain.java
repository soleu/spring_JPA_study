package jpql;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.close();
//JPQL - case
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금' " +
//                            "     when m.age >= 60 then '경로요금' " +
//                            "     else '일반요금' "+
//                            "end " +
//                            "from Member m";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }
            //엔티티 프로젝션

//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
            //엔티티 프로젝션 (리스트) - join 을 넣어서 명시
//            List<Team> result= em.createQuery("select t from Member m join m.team t",Team.class)
//                    .getResultList();
//임베디드 타입 프로젝션 (엔티티부터 시작)
//           em.createQuery("select o from Order o", Address.class)
//                    .getResultList();
//            // 스칼라 타입 프로젝션(여러값)
            List resultList = em.createQuery("select distinct m.username,m.age from Member m", Member.class)
                    .getResultList();

            //조회 -1
            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);
            // 조회 -2 이 방법 사용하기
            List<MemberDTO> resultList1 = em.createQuery("select  new jpql.MemberDTO(m.username,m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = resultList1.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());

//            Member findMember = result.get(0);
//            findMember.setAge(20);//영속성 컨텍스트로 관리됨

//            // 쿼리 문 기본 구조
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query1 = em.createQuery("select m.username from Member m", String.class);
//            Query query2 = em.createQuery("select m.username,m.age from Member m"); //타입을 특정 지을 수 없을때는 query 사용

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
