package jpabook.jpashop.domain;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
    String city;
    String street;
    String zipCode;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
