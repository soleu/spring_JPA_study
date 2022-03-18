package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링 컨테이너 안에서 테스트 돌아가게
@Transactional
class MemberServiceTest {
//테스트 요구사항
//        회원가입을 성공해야한다.
//        회원가입 할 떄 같은 이름이 있으면 예외가 발생해야한다.

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    // rollback + sql 로그 찍히는거 보는 법
//    @Autowired
//    EntityManager em;
//    then에서 em.flush();

    @Test
    @Rollback(value = false) //쿼리가 나가게. 기본적으로 테스트는 db에 저장이 되면 안됨
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    //중복 회원 예외
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

//        try {
//            memberService.join(member2); //예외 발생 시나리오
//        } catch (IllegalStateException e) {
//            return;
//        }
        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
        //        fail("예외가 발생해야 한다."); //정상실행될시, 잘못된 흐름이므로 fail 처리
    }
}