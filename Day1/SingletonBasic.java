package review.flab.vincent;

public class SingletonBasic implements Singleton{

    //목표 : 기본적인 싱글턴 패턴을 구현해보자.

    //싱글턴 패턴의 정의 : 객체의 인스턴스를 여럿 두지 않고, 하나만 두는 패턴.

    //1.기본 생성자를 private 으로 선언해서 외부에서 new 키워드로 인스턴스를 생성할 수 없게 막는다.
    private SingletonBasic(){
    }

    //2. 외부에서 접근해서 인스턴스를 가져갈 수 있는 메소드를 노출한다.
    public static SingletonBasic getInstance(){
        //2-1. 이 메소드의 제어자는 static 이어야 한다. 위에서 객체 생성을 막았기 때문에 객체 생성 없이 메소드를 호출할 방법은 static 밖에 없기 때문.

        /*
        SingletonBasic instance = new SingletonBasic();
        return sb;
        2-2. 이런 식으로 메소드 내에서 new 를 써서 새 인스턴스를 만들어서 리턴해버리면, 메소드가 호출될 때마다 새로운 인스턴스가 만들어질 것이다. 싱글턴 패턴의 정의와 어긋난다.
         */
        return instance;
    }
    //3. 위 메소드에서 리턴하기 위한 인스턴스를 선언한다.
    //   메소드가 몇 번 호출되던간에 언제나 똑같은 하나의 인스턴스를 리턴해야하므로 필드로 선언해두는 것이 적절하다.
    //   2의 메소드가 static 이므로 이 필드 또한 static 이어야 한다.
    private static final SingletonBasic instance = new SingletonBasic();
}


/* 남은 질문

1.  위 예제에서 instance 필드가 굳이 final 일 필요가 있을까?
    이 클래스에서 내가 setInstance 같은 메소드를 구현하지 않는 이상 외부에서는 어차피 instance 의 참조를 변경할 방법이 없지 않나?
    바꿔치기할 다른 SingletonBasic 클래스의 인스턴스를 얻을 수도 없을 것이고.
 
 */
