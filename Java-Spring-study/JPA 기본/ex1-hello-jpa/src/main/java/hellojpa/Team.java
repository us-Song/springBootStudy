package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id @GeneratedValue
    @Column(name= "TEAM_ID")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")//원래 다대일일 경우 mappedby= "team" 이지만, 일대다에서는 team이 주인인까 안함
    //@JoinColumn(name= "TEAM_ID") //일대다, 주인이지만 외래키는 member테이블에 있으므로 joincolumn을 꼭 해줘야함, 그래야 jpa가 관리해줌
    private List<Member> members = new ArrayList<>();
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
