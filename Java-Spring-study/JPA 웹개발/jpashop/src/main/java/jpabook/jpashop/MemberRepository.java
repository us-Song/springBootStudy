package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext //EntityManager를 빈으로 주입할 때 사용하는 어노테이션 ,스프링이 만들어둔 EntityManager를 주입받을 때 사용
    private EntityManager em; // 스프링 부트가 생성하고 주입

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 사이트이펙트를 고려해서 리턴값 최소화
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
