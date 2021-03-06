package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
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
            //persist 3개로 작성 -> parent 하나로 child 2개가 관리됐으면 함! => cascade
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);//parent에 cascade를 사용하면 child 두개가 persist 됨(영속성 전이)_
//            em.persist(child2);
//            Movie movie=new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께사라지다.");
//            movie.setPrice(10000);

//            em.persist(movie);

//            //단방향 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
            Member member = new Member();
//            member.setCreatedBy("kim");
//            member.setCreatedTime(LocalDateTime.now());
            member.setUsername("member1");
//            member.setTeam(team); //객체로 바로 넣음
            em.persist(member);
//
            em.flush();
            em.clear();//영속성 초기화


            //단방향 조회
            Member findMember = em.find(Member.class, member.getId());
            //프록시
//            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());//DB 조회X
            System.out.println("findMember.username = " + findMember.getUsername());//DB 조회(쿼리 날림)

//            //프록시 초기화 여부 확인
//            System.out.println("isLoaded = "+ emf.getPersistenceUnitUtil().isLoaded(findMember));
//            //프록시 클래스 인지 확인
//            System.out.println("findMember ="+findMember.getClass());
//            //프록세 강제 초기화
//            Hibernate.initialize(findMember);
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

