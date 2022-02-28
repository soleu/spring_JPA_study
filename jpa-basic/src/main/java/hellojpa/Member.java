package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")//컬럼 이름 설정
    private String username;

    @ManyToOne //단방향 ORM 매핑(양방향도 동일)
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    //    private Integer age;
//
//    @Enumerated(EnumType.STRING)//DB에 enum이 없어서, string으로 설정됨
//    private RoleType roleType;
//


//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;

    //    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
    //
//    private LocalDate testLocalDate; //년월
//    private LocalDateTime testLocalDateTime; //년월일
//
//    @Lob//큰거(blob)
//    private String description;
    @OneToOne //일대일
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    //set보다는 change같은걸 사용하면서 차이를 두기(확인하기 쉽게)
    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
