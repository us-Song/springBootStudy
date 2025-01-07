package jpql;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //설정정보
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티매니저팩토리(관례)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member = new Member();
            member.setUserName("회원1");
            member.setAge(19);
            member.setTeam(teamA);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setUserName("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUserName("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

//            em.flush();
//            em.clear();
            //벌크 연산 수행시 flush 자동 호출
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            System.out.println("resultCount = " + resultCount);

            em.clear();

            Member member1 = em.find(Member.class, member.getId());
            System.out.println("member.getAge() = " + member1.getAge());

            //String query = "select function('group_concat', m.username) from Member m";
//            String query = "select group_concat(m.username) from Member m"; //이렇게 쓰는게 직관적.
//            String query = "select m.username From Member m"; 단일 값 연관 경로
//            String query = "select m.team From Member m"; // 묵시적 조인 발생 : 객체 입장에서 생각하면 그냥 가져오면 되겠지만, db 관점에서 보면 멤버 테이블에서 팀을 찾는 것이므로 팀 테이블과의 조인이 필요함
//            String query = "select t.members From Team t"; //컬렉션 탐색
//            String query = "select m.username From Team t join t.members m"; //컬렉션의 상태까지 ㅎ탐색하려면 명시적 조인 사용
//            String query = "select SIZE(t.members) From Team t"; // 컬렉션 사이즈 확인시에는 Size함수 사용
//            Integer result = em.createQuery(query, Integer.class).getSingleResult();

//            String query = "select m from Member m";
//            String query = "select m from Member m join fetch m.team"; 페치조인
//            String query = "select t from Team t";
//            String query = "select m from Member m where m.team = :team";
//            String query = "select t from Team t join fetch t.members";
//            Collection result = em.createQuery(query, Collection.class)
//                            .getResultList();
//            System.out.println("result = " + result);
//            Member findMember = em.createQuery(query, Member.class)
//                    .setParameter("team", teamB)
//                    .getSingleResult();
//
//            System.out.println("findMember = " + findMember);
//            for (Member member1 : resultList) {
//                System.out.println("member = " + member1.getUserName() + "," + member1.getTeam().getName());
//                //회원1: TeamA, 지연로딩이므로 쿼리날라감
//                //회원2: TeamA가 이미 영속성 컨텍스트에 있으므로 쿼리 x, 캐시에서 꺼내옴
//                //회원3: TeamB는 지연로딩이므로 영속성컨텍스트에 없움 , 쿼리날려서 가져옴
//                //-->페치쿼리 안쓰면 쿼리 총 3번 날라감 -> N+1문제 -> 페치조인으로 해결해야함
//            }

//            for (Team team1 : resultList) {
//                System.out.println("team = " + team1.getName() + "," + team1.getMembers().size());
//                for(Member member1 : team1.getMembers()) {
//                    System.out.println("member1.getUserName() = " + member1.getUserName());
//                }
//            }
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
