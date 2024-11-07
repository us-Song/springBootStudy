package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name= "MEMBER_ID")
    private Long id;
    @Column(name= "USERNAME", nullable = false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)//일대다 양방향, 주인이 아니니까 읽기전용으로 만들ㅇㅓ버림
    private Team team;

    @OneToOne
    @JoinColumn(name= "LOCKER_ID") //외래키
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

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

    public Member() {
    }
}
