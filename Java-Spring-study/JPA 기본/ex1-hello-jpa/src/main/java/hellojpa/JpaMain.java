package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //설정정보
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티매니저팩토리(관례)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("A");
            Member member2 = new Member();
            member2.setUsername("B");
            Member member3 = new Member();
            member3.setUsername("C");
            Member member4 = new Member();
            member4.setUsername("D");
            Member member5 = new Member();
            member5.setUsername("E");
            System.out.println(" =============== ");
            em.persist(member1); //디비에서 시퀀스 가져옴
            em.persist(member2); //mem에서 시퀀스 가져옴
            em.persist(member3); // mem에서 시퀀스 가져옴
            em.persist(member4); // mem에서 시퀀스 가져옴
            em.persist(member5); // mem에서 시퀀스 가져옴

            System.out.println("member.getId() = " + member1.getId());
            System.out.println("member.getId() = " + member2.getId());
            System.out.println("member.getId() = " + member3.getId());
            // select 쿼리가 필요없는 이유는 , jdbc내부적으로 insert쿼리 실행시 리턴으로 값을 받음, 이 값을 jpa가 내부적으로 들고옴
            System.out.println(" =============== ");
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
