package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();

     @AfterEach
     public void afterEach(){
            repository.clearStore();
     }

    @Test
     public void save(){
         Member member= new Member();
         member.setName("Spring");

         repository.save(member);

         Member result= repository.findById(member.getId()).get();
         //System.out.println("result = " + (result == member));
         //Assertions.assertEquals(member,result); //매번 값으로 볼 수 없으니까 assertions 를 이용 참일 때 파란불, 거짓이면 빨간불 (저장한 값, 실제 값)
         assertThat(member).isEqualTo(result);// 이게 더 편해서 자주 쓰임,assertj
     }

     @Test
    public void findByName(){
         Member member1=new Member();
         member1.setName("spring1");
         repository.save(member1);

         Member member2 =new Member();
         member2.setName("spring2");
         repository.save(member2);

         Member result = repository.findByName("spring2").get();

         assertThat(result).isEqualTo(member2);
     }

     @Test
    public void findAll(){
         Member member1= new Member();
         member1.setName("spring1");
         repository.save(member1);

         Member member2= new Member();
         member2.setName("spring2");
         repository.save(member2);

         List<Member> result = repository.findAll();

         assertThat(result.size()).isEqualTo(2);

     }
}
