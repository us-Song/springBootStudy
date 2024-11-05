package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name= "MEMBER_ID")
    private Long id;
    @Column(name= "USERNAME", nullable = false)
    private String username;
//    @Column(name = "TEAM_ID")
//    private Long teamId;
    @ManyToOne //멤버와 팀 관계는 N:1
    @JoinColumn(name= "TEAM_ID") // 조인하는 컬럼
    private Team team;

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

    public Member() {
    }
}
