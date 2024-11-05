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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            Team team2 = new Team();
            team2.setName("TeamB");
            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId()); // 영속성 컨텍스트에서 가져와서 쿼리 안날림, 멤버와 팀을 한번에 땡겨옴
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            findMember.setTeam(team2); //멤버가 가입한 팀 변경 -> member의 외래키인 team_id 수정

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
