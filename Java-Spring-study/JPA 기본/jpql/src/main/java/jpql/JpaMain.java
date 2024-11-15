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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("ㄹㅇ");
            member.setAge(20);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUserName("zz");
            em.persist(member2);
            em.flush();
            em.clear();

            //String query = "select function('group_concat', m.username) from Member m";
            String query = "select group_concat(m.username) from Member m"; //이렇게 쓰는게 직관적.

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();
            for (String member1 : resultList) {
                System.out.println("member1 = " + member1);
            }
            tx.commit();
            //            String query = "select mm.mAge from (select m.age as mAge from Member m) as mm";
//            -> 프롬절 서브쿼리 쓸 때 컬럼에도 별칭 붙여줘야함
        } catch (Exception e) {
            tx.rollback();
            System.out.println("e.getMessage() = " + e.getMessage());
        } finally {
            em.close();
        }
        emf.close();
    }
}
