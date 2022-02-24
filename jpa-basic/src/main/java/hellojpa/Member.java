package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")//컬럼 이름 설정
    private String username;

    @ManyToOne //단방향 ORM 매핑
    @JoinColumn(name = "TEAM_ID")
    private Team team;
//
//    private Integer age;
//
//    @Enumerated(EnumType.STRING)//DB에 enum이 없어서, string으로 설정됨
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate; //년월
//    private LocalDateTime testLocalDateTime; //년월일
//
//    @Lob//큰거(blob)
//    private String description;


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

    public void setTeam(Team team) {
        this.team = team;
    }
}
