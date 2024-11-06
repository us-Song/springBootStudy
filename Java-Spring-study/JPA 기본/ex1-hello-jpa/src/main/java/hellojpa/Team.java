package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name= "TEAM_ID")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team") // 나의 반대편 연결 상대에 이거와 매핑되어있다
    private List<Member> members= new ArrayList<>();
    public Long getId() {
        return id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
