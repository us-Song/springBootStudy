package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {
    //@Column(length=10) 같은 제약도 되도록 추가해주면 좋다 , 가급적 제약조건 다 넣는게 좋다, 테이블 보고 왔다갔다하는거 방지
    //컬럼명 소문자 대문자는 회사 마다 규칙 다름, 보통 itemId면 ITEM_ID 이런식으로 언더바 넣는게 관례, 부트는 _ 관례를 적용해줌
    //개인 스타일임
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")//애매한건 직접매핑, 매핑 안하면 객체명이랑 동일
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
