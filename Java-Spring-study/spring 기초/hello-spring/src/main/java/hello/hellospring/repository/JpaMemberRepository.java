package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 매니저로 모든게 동작, 스프링 부트가 알아서 만들어줌 -> 만들어준걸 인젝션 받으면됨 다 내부에서 처리

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //insert 다해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member=em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    //단건이 아닌 리스트를 가지고 여러값을 가지고 돌릴 때는 jpql을 사용해야함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class).
                setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class).getResultList(); //객체 자체를 조회
    }


}
