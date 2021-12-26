package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//      basePackages = "hello.core.member",  // 하위 컴포넌트 읽음
//      basePackageClasses = AutoAppConfig.class, // AutoAppConfig 위치인 hello.core에 있는 컴포넌트 읽음
//      default : @ComponentScan을 붙인 클래스의 위치인 hello.core 부터 하위 패키지의 컴포넌트 전부 읽음 
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();

//    }
}
