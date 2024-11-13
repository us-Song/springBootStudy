package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    //@Column(length=10) 같은 제약도 되도록 추가해주면 좋다 , 가급적 제약조건 다 넣는게 좋다, 테이블 보고 왔다갔다하는거 방지
    //컬럼명 소문자 대문자는 회사 마다 규칙 다름, 보통 itemId면 ITEM_ID 이런식으로 언더바 넣는게 관례, 부트는 _ 관례를 적용해줌
    //개인 스타일임
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")//애매한건 직접매핑, 매핑 안하면 객체명이랑 동일
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")// 외래키
    private List<Order> orders = new ArrayList<>();//비즈니스 적으로 봤을 때, 멤버에서 주문 내역을 찾기보단 오더 테이블의 member를 이용해서 주문 내역을 찾는게 설계적으로 맞다 .
    // 그렇기 때문에 굳이 양방향 관계를 맺을 필요가 없다, 여기선 예제니까 비즈니스적으로 필요없어도 맺는거임

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
