package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {
    public static void main(String[] args) {
        //설정정보
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = new Order();
            em.persist(order);

            //하단 코드 처럼 단방향으로도 아무 문제 없다 단지 복잡하고 객체지향에 어울리지 않을 뿐
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
//하단 코드는 양방향 연관관계 맺을 시 사용 가능한 코드, 참조를 통해 간단하게 사용 가능, JPQL 사용시 편의를 위해 양방향 맺는 경우가 많다
//            order.addOrderItem(new OrderItem());
            // 핵심은 웬만하면 단방향으로 해라, 단방향으로 잘 설계하기
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
