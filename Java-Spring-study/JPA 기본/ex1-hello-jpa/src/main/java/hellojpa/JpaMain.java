package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //설정정보
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //엔티티매니저팩토리(관례)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0);
            em.remove(findParent);
//            Member refMember = em.getReference(Member.class, member1.getId());//프록시 객체 생성, member의 id는 파라미터로 넘어갔으니까 프록시 객체가 알 수 있음
//            Member m = em.find(Member.class, member1.getId());//진짜 객체 생성, 여기선 member 전부를 들고옴

            System.out.println(" =======");
            System.out.println(" =======");
//            m.getUsername(); // 프록시 객체는 파라미터로 넘어온 id만 알고 나머지 실제 객체에 대한 정보를 모름 (target에 값이 없음)
            // -> 영속성 컨텍스트에 실제 객체 초기화 요청 -> DB select 쿼리 발생 -> 실제 객체 생성 후 프록시의 타겟과 연결 시켜줌 -> 프록시 객체로 실제 객체 참조 가능
            System.out.println(" =======");

            //프록시의 특징 중 4번째 특징에 대한 부연 설명
            //System.out.println("a == a :  " + (m1 == reference)); // 한 트랜잭션 안에서 같은 영속성 컨텍스트에서 값을 꺼냈으면 JPA는 항상 == 을 보장
            // 이미 영속성 컨텍스트에 올려놨는데 , 프록시를 굳이 사용할 필요가 없으니까 프록시를 사용해도 영속성 컨텍스트에 올라가있는 실제 객체를 가져온다
            //둘 다 프록시 객체여도 == 을 보장 , 첫 프록시 객체 사용시 영속성 컨텍스트에 프록시가 올라가므로 두번째 프록시도 첫번째에 올라간 프록시 사용해서 == 보장
            //첫번째로 프록시 객체를 영속성 컨텍스트에 올리고, 두번째에 실제 객체를 find 해도 프록시 반환 m1==m2 -> JPA는 한 트랜잭션 내에서 == 을 항상 보장
            //내 개인적으로 생각해보면 한 트랜잭션 내에서 영속성 컨텍스트에 이미 올려놨는데 객체가 다르다고 같은 값을 위해 다시 db를 뒤져서 영속성 컨텍스트에 요청하는 행위는 안할 것 같음
            // 그렇기 때문에 한 트랜잭션 내에서 같은 값을 가진 프록시 객체 와 진짜 객체의 타입을 비교했을 때 타입이 같음을 보장
            //이득도 없기도 하고 JPA가 제공하는 기본 매커니즘임

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
