package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class Delivery extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;
    String city;
    String street;
    String zipCode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
