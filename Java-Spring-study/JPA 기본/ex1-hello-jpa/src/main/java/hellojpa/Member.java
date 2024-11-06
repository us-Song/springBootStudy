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

    public void changeTeam(Team team) {
        //getter setter를 쓰면 , 관례상 쓰는 것처럼 보여 안중요한 로직 처럼 보여지기 때문에 메서드 명을 바꿔준다.
        this.team = team;
        team.getMembers().add(this);// 멤버 팀 양쪽에 주인의 값을 세팅하기 위해 코드 추가
    }

    public Member() {
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                '}';
    }
}
