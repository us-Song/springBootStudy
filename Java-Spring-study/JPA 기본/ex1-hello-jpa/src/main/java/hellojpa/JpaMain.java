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
//            team.getMembers().add(member);
            em.persist(team);


            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //주인에 값넣기
            em.persist(member);

//            team.addMember(member); 연관관계 편의 메서드는 한쪽에만 만들고 사용, 둘중에 한군데서만 주인에 값넣기 , 어디에 만들지는 상황마다 다름
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println(" =========================== ");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println(" =========================== ");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
