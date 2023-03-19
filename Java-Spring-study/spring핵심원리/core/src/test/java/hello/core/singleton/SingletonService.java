package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance=new SingletonService(); //static으로 선언하면 클래스 레밸로 올라가기 떄문에 딱 하나존재

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
