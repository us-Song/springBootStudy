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
            //저장
            Member member = saveMember(em);

            Team team = new Team();
            team.setName("TeamA");
            //옆 테이블 업데이트
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static Member saveMember(EntityManager em) {
        Member member= new Member();
        member.setUsername("member1");

        em.persist(member);
        return member;
    }
}
