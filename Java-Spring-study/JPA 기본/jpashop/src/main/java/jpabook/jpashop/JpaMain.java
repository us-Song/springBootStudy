package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.*;

public class JpaMain {
    public static void main(String[] args) {
        //설정정보
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            OrderItem orderItem1 = new OrderItem();
//            OrderItem orderItem2 = new OrderItem();
//            Order order = new Order();
//            order.getOrderItems().add(orderItem1); // 주인 세팅을 안함.
//            order.getOrderItems().add(orderItem2); // 주인인 orderItem은 부모가 누군지 모름 -> 부모인 order가 orderitem 관리 불가
//            order.addOrderItem(orderItem1); // 편의 메서드를 통해 주인까지 세팅
//            order.addOrderItem(orderItem2); //따라서 주인인 oderitem은 부모인 order의 정보를 들고 있게됨 -> 부모인 order가 orderitem 관리
//            em.persist(order);

//            em.flush();
//            em.clear();

//            Order findOrder = em.find(Order.class, order.getId());
//            em.remove(findOrder);
            System.out.println("========ttt===== ");
            Book book = new Book();
            book.setAuthor("송");
            book.setName("dd");
            em.persist(book);

            em.createQuery("select i from Item i where type(i)=Book", Item.class)
                    .getResultList();
            System.out.println("============= ");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
