package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L; //1,2,3.. 값 순서

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 일경우 고려
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny(); //java 람다
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }



    public void clearStore()
    {
        store.clear();
    }
}
