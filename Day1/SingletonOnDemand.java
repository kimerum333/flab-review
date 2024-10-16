package review.flab.vincent;

public class SingletonOnDemand {
    //목표 : 직전에 만든 싱글턴 패턴 클래스 SingletonBasic 의 문제점을 개선하자.
    //문제점 : static 필드로 인스턴스가 선언되어 있다. 이러면 사용 여부와 관계 없이 JVM 에 해당 클래스가 로드되는 순간 메모리를 잠식하게 된다.

    //0. 이전과 마찬가지로 기본 생성자를 private 으로 둬서 접근할 수 없게 한다.
    private SingletonOnDemand(){
    }

    //1. null 로 초기화 또는 선언만 해두고 초기화하지 않는 방식을 사용. 객체의 인스턴스를 미리 만들어두지 않는 쪽으로 바꾼다.
    private static SingletonOnDemand instance;

    //2.getInstance 에 field 가 null 이면 만들고, 있으면 있는 것을 반환하는 조건문을 삽입.
    public static SingletonOnDemand getInstance(){
        if(instance==null){
            instance = new SingletonOnDemand();
        }
        return instance;
    }
}

/*
* 추가 조사
*   1. 위의 해결방법을 lazy initialization 이라고 하는 모양이다.
*   2. 한번 더 개선해야 할 점이 남아있다. 멀티 스레드 환경을 생각해보자. 두 개의 스레드가 동시에 getInstance 의 최초 호출을 시도할 경우 문제가 생긴다.
*       국비때 배운 스레드 개념에 의거하면, getInstance 메소드에 synchronized 모디파이어를 사용해서 스레드 세이프하게 만들어줄 수 있을 것이다.
*       단, synchronized 는 반드시 성능 저하를 불러오므로 좋지 않다는 것도 함께 배웠다.
*
*   3. 개선책 1: double lock - 1차 if 문에서 instance 가 null 임을 확인, 2차 if 문에서 synchronized 를 활용하는 방식.
*       거의 동일한 시기에 들어온 getInstance 최초 호출은 1차 lock 은 동시에 통과할지 몰라도 2차 lock 에서 걸러진다.
*       이후의 getInstance 호출은 1차 lock 에서 걸러지면서 동기화 블록을 만나지 않으므로 성능 문제가 많이 개선된다.
*       또한 아래와 같은 익숙지 않은 '동기화 블록' 문법을 새로 학습했다.
*       synchronized(MyClass.class){
*       }
* */
