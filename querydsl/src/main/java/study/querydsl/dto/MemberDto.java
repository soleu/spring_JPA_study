package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {
    private String username;
    private int age;


    public MemberDto() {
    }
    @QueryProjection //dto도 큐타입으로 생성
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
