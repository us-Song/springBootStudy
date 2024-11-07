package hellojpa;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("A") //디폴트는 클래스명
public class Album extends Item{
    private String artist;
}
