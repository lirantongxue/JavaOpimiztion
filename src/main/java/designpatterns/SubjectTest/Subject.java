package designpatterns.SubjectTest;

import java.util.Vector;

/**
 * 悲观者实现:
 *
 * @auther liran
 * @create 2018-09-03-下午6:28
 */
public class Subject implements ISubject {

    Vector<IObserver> iObservers=new Vector<>();
    @Override
    public void attach(IObserver observer) {
        iObservers.addElement(observer);
    }

    @Override
    public void inform(int state) {
        if(state==5) {
            for(IObserver observer: iObservers) {
                observer.upDate(state);
            }
        }
    }
}
