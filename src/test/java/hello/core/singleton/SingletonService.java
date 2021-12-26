package hello.core.singleton;

public class SingletonService {

    private  static  final SingletonService instance = new SingletonService();
    // 자바가 딱 뜨면서 static 영역의 instance를 초기화 하고 SingletonService 생성
    //
    public  static  SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
