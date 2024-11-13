package jpabook.jpashop.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Movie extends Item{
    private String director;
    private String actor;

    @Embedded
    private Address address;
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
