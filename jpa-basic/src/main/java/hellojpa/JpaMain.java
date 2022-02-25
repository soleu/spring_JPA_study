package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


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
            Movie movie=new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다.");
            movie.setPrice(10000);

            em.persist(movie);

//            //단방향 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setTeam(team); //객체로 바로 넣음
//            em.persist(member);
//
//            em.flush();
//            em.clear();
            //단방향 조회
//            Member findMember=em.find(Member.class,member.getId());
//            Team findTeam=findMember.getTeam();
////양방항 조회
//            Team findTeam = em.find(Team.class, team.getId());
//            List<Member> members = findTeam.getMembers();
//
//            for (Member m : members) {
//                System.out.println("m = " + m.getUsername());
//            }
            //CREATE
            //비영속
//            Member member = new Member();
////            member.setId("A");
//            member.setUsername("A");
//            member.setRoleType(RoleType.USER);
//            System.out.println("=========");
//            em.persist(member);
//            System.out.println("=========");
//            System.out.println("member.id = "+member.getId());
            //영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");

//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("=== AFTER ===");
            //READ
//            Member findMember = em.find(Member.class, 1L);
//            em.detach(member1);//준영속 상태로 만들어서 JPA에서 관리를 하지 않음.
//            member1.setName("AAA"); //쿼리 실행X
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

