package JavaOptimization.ObserverDemo;/**
 * Created by liran on 17/7/18.
 */

/**
 * a:
 *
 * @auther liran
 * @create 2017-07-18-下午3:52
 */
public class ObservaTest {

        public static void main(String[] args) {
            ObservableLeo number = new ObservableLeo();    //被观察者对象
            number.addObserver(new ObserverLeo());    //给number这个被观察者添加观察者(当然可以有多个观察者)
            number.setData(1);
            number.setData(2);
            number.setData(3);
        }
}
