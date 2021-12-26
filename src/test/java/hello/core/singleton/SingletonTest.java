package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회 : 호출할 떄 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

//       문제점)클라이언트요청이 많음 -> tps 5만...(배민) -> 메모리 효율 개구데기
//       방안) 하나만 만들어서 공유해라
//      memberService1 = hello.core.member.MemberServiceImpl@43c1b556
//      memberService2 = hello.core.member.MemberServiceImpl@587e5365
    }
//    public static void main(String[] args) {
//        SingletonService test1 = new SingletonService();
//    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService(){
//        new SingletonService();
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService2 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
//        singletonService2 = hello.core.singleton.SingletonService@2002fc1d
//        singletonService2 = hello.core.singleton.SingletonService@2002fc1d
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너의 사용")
    void springContainer(){
//      SingletonService singletonService1 = SingletonService.getInstance();
//      SingletonService singletonService2 = SingletonService.getInstance();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        Assertions.assertThat(memberService1).isSameAs(memberService2);
//            memberService1 = hello.core.member.MemberServiceImpl@3d97a632
//            memberService2 = hello.core.member.MemberServiceImpl@3d97a632

    }
}
