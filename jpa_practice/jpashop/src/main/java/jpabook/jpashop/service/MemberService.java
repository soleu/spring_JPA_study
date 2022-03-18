package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service //어노테이션으로 스프링빈에 컴포넌트 등록
@Transactional(readOnly = true) //데이터 변경하기 위해서는 트랜젝션 어노테이션이 꼭!!! 필요(javax,spring)이 있지만 스프링 어노테이션을 많이썼으므로 그거 사용
@RequiredArgsConstructor //final만 가지고 생성자를 만들어줌
public class MemberService {

    //    @Autowired //필드 인젝션 방식(단점 많. 수정 못함)
    private final MemberRepository memberRepository;

    //setter Repositroy inject함 - 로딩시점에 조립이 이미 다 끝남
//// -> 생성자 인젝션 추천(명확하게 컴파일 에러 캐치 가능)
//    @Autowired //autowired 어노테이션이 없어도 최신 스프링에서는 하나만 있는 생성자에 처리해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional //수정이 들어가므로 어노테이션 따로 설정
    public Long join(Member member) {
        validateDuplicateMember(member);// 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); //이름이 중복되는 경우도 있어서 unique로 하기
        if (!findMembers.isEmpty()) { // findMembers>0이면 error도 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회 -> 조회에서는 @Transactional(readOnly=true) 하면 성능 최적화
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
