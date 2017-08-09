package JavaOptimization.ObserverDemo;/**
 * Created by liran on 17/7/18.
 */

import java.util.Observable;

/**
 * Leo观察者:
 *
 * @auther liran
 * @create 2017-07-18-下午3:16
 */
public class ObservableLeo extends Observable {

    int data = 0;

    public void setData(int i) {
        data = i;
        setChanged();    //标记此 Observable对象为已改变的对象
        notifyObservers();    //通知所有观察者
    }

}
