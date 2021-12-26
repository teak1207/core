package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService ;
    OrderService orderService ;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void createOder(){
        Long memberId = 1L;   // Long을 쓰는 이유 ==> Null을 쓸수 있음
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order =  orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getItemDiscount()).isEqualTo(1000);
    }
}
