package javaoptimization.ObserverDemo;/**
 * Created by liran on 17/7/18.
 */

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者:
 *
 * @auther liran
 * @create 2017-07-18-下午3:51
 */
public class ObserverLeo implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        ObservableLeo myObserable=(ObservableLeo) o;     //获取被观察者对象
        System.out.println("发现了被观察者Date数据改变Data has changed to " +myObserable.data);
    }
}
