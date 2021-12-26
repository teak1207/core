package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration   // 배치 , AppConfig에 설정을 구성한다는 뜻 , 구성정보
public class AppConfig {

//      @Bean  memberService -> new memberRepository()
//      @Bean  OrderService  -> new memberRepository()


    @Bean  //스프링 컨테이너에 스프링 빈으로 등록
//  @Bean(name = "mmm")  ,  ==> default 를 따르자..
    public MemberService memberService(){      //  <<--  역할을 세우고 
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //  <<--  구현을 안에 세운다
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }


//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), new FixDiscountPolicy());
//    }
    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }


 }

