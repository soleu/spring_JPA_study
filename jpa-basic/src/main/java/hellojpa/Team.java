package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAD_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //양방향 매핑, mappedBy: 반대방향 표시
    List<Member> members = new ArrayList<Member>();
//연관관계갸 양쪽에 있으면 무한루프가 될수 있음(둘 중 하나)
//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
