package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    Long id;

    private String name;
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")) //중간테이블 (일대다 다대일로 다대다 관계로 풀기위해 중간테이블사용)
    private List<Item> items = new ArrayList<>();


    //계층 구조는 현재의 객체 기준으로 생각해서 매핑 ( 자기 자신을 기준으로 점점 계층화하며 내려가는것)
    @ManyToOne //현재 객체인 카테고리 입장에서 부모는 하나고 , 카테고리는 여러개 parent(one) - category(many)
    @JoinColumn(name = "parent_id")
    private Category parent; // 현재 객체인 카테고리의 부모를 선언한 것
    //parent - category - child 로 이어지는 계층구조 ( 계층 구조는 현재 객체(@Entity) 기준으로 생각하자 지금은 Category 기준 생각)
    @OneToMany(mappedBy = "parent") // category (one) - child(many) (하나의 카테고리에는 여러개가 존재하니까 당연함)
    private List<Category> child = new ArrayList<>();

}
