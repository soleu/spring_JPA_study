package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street1", "10001"));
            member.getAddressHistory().add(new Address("old2", "street2", "10002"));

            em.persist(member);//persist를 하나만 했는데 값 타입 컬렉션을 통해 여러개의 테이블이 insert

            em.flush();
            em.clear();
            System.out.println("-------start----------");
            Member findMember = em.find(Member.class, member.getId());

            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            //homeCity->newCity
//            findMember.getHomeAddress().setCity("newCity"); //이렇게 하면 안됨(실행은 되지만)
            Address a =findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));//이런식으로 새로운 객체 생성후 갈아끼기

            //치킨 -> 한식 (String Array 경우)
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address("old1", "street1", "10001"));
            findMember.getAddressHistory().add(new Address("new1", "street1", "10001"));

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
