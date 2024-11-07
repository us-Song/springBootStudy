package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToMany
    @JoinTable(name= "CATEGORY_ITEM", joinColumns = @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID"))//중간테이블 매핑
    private List<Item> items = new ArrayList<>();

    //셀프 매핑, 상위, 하위 카테고리를 위해 셀프 매핑
    @ManyToOne
    @JoinColumn(name= "PARENT_ID")
    private Category parent;
    @OneToMany(mappedBy = "parent") //부모카테고리 1개 밑에 여러개의 하위 카테고리 one to many
    private List<Category> child= new ArrayList<>();

}
