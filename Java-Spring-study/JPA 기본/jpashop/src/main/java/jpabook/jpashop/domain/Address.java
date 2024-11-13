package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.Objects;

@Embeddable
public class Address {
    @Column(length = 10) // 이렇게 제약 조건도 Address를 값으로 쓰는 곳에서 공통으로 적용 가능
    private String city;
    private String street;
    private String zipcode;

    public String fullAddress() {
        //값 타입을 사용하면 이런식으로 의미있는 메서드를 만들 수 있다 , Address를 값으로 쓰는 곳에서 공통으로 사용 가능
        return getCity() + getStreet() + getZipcode();
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

    private void setStreet(String street) {
        this.street = street;
    }

    private String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        //getter를 안쓰고 객체를 그냥 쓸 경우에는 프록시 객체인 경우 객체 접근이 안되므로 getter를 써서 진짜 객체에 접근 할 수 있도록 하자
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}

