package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        //bean 안에 정의된 메서드 이름들을 가져다가 배열에 저장
        String []   beanDefinitionNames  = ac.getBeanDefinitionNames();
        //iter + enter => for 자동 생성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            //soutm
            //soutv
            System.out.println("beanDefinitionName +  = " + beanDefinitionName+  "object = "+ bean );
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        //bean 안에 정의된 메서드 이름들을 가져다가 배열에 저장
        String []   beanDefinitionNames  = ac.getBeanDefinitionNames();
        //iter + enter => for 자동 생성
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // getBeanDefinition : bean 하나하나의 메타데이터를 받을 수 있음

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //BeanDefinition.ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
                //BeanDefinition.ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("beanDefinitionName +  = " + beanDefinitionName+  "object = "+ bean );

            }

        }
    }

}

