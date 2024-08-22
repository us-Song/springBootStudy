package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

//Repository 어노테이션이 붙은 클래스는 DB에 접근하는 클래스
@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static ㅅㅏ용
    private static long sequence = 0L; //static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: memeber={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for(Member m : all) {
//            if(m.getLoginId().equals((loginId))) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();
// 람다랑 스트림은 기본으로 알아야함.
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clearStore() {
        store.clear();
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
