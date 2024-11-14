package jpql;

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
            Member member = new Member();
            member.setUserName("song");
            member.setAge(19);
            em.persist(member);

            Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class).
                    setParameter("username", "song").getSingleResult();
            System.out.println("singleResult.getUserName() = " + singleResult.getUserName());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e.getMessage() = " + e.getMessage());
        } finally {
            em.close();
        }
        emf.close();
    }
}
