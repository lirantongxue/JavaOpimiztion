package designpatterns.SubjectTest;

/**
 * 观察者:
 *
 * @auther liran
 * @create 2018-09-03-下午6:20
 */
public class Observer  implements  IObserver{


    @Override
    public void upDate(int state) {
        System.out.println("发现被观察者状态发生改变。被观察者的状态为"+state+"观察完毕");
    }
}
