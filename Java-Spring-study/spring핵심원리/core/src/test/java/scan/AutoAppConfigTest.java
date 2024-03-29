package scan;

import hello.core.AutoAppConfig;
import hello.core.common.MyLogger;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;


public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository= bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);

//        MyLogger myLogger = ac.getBean("myLogger", MyLogger.class);
//        Assertions.assertThat(myLogger).isInstanceOf(MyLogger.class);
//        System.out.println("myLogger = " + myLogger);

    }
}
