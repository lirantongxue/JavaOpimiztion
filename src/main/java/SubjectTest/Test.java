package SubjectTest;

/**
 * 测试观察者模式:
 *
 * 一： 创建观察者接口 并定义:观察者依据被观察者状态，而要是现实的动作。
 *     创建接口， 以及实现类。
 *
 * 二： 创建被观察接口 并定义: 被观察的状态方法或者动作，作为被观察目标。 并提供容器的入口，来添加多个观察者与自身内联。
 *      目的是，当被观察的状态发生改变时，  遍历通知每个观察者。 通知的动作有被观察者发起。
 *
 *
 *     观察者 与  被观察者是   多对一的关系。
 *
 * @auther liran
 * @create 2018-09-03-下午6:39
 */
public class Test {

    public static void main(String[] args) {

        IObserver iObserver1=new Observer();
        IObserver iObserver2=new Observer();

        ISubject subject =new Subject();

        subject.attach(iObserver1);
        subject.attach(iObserver2);
        subject.inform(5);



    }


}
