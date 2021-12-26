package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext( TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 중복오류가 난다")
    void findBeanByParentTypeDuplicate(){
    //   DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName(){
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        DiscountPolicy rateDiscountPolicy =  ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
     // DiscountPolicy rateDiscountPolicy2 =  ac.getBean("fixDiscountPolicy", DiscountPolicy.class);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
//      System.out.println("rateDiscountPolicy = " + rateDiscountPolicy2);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        RateDiscountPolicy rateDiscountPolicy =  ac.getBean( RateDiscountPolicy.class);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String,DiscountPolicy>  beansOfType =  ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"value"+ beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기  --> Object")
    void findAllBeanByObjectType(){
        Map<String,Object>  beansOfType =  ac.getBeansOfType(Object.class);
//      assertThat(beansOfType.size()).isEqualTo(2);
        /*
        expected: 2
        but was: 16
        Expected :2
        */
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"value"+ beansOfType.get(key));
        }
        //최상위 Object를 불러왔기에 rate+fix 이외의 것들이 다나옴
    }

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
