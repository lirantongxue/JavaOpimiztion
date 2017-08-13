package JavaOptimization.ThreadDemo.ProducerDemo;
/**
 * Created by liran on 17/8/13.
 */

/**
 * 作为生产和消费者之间的共享数据模型:
 *
 * @auther liran
 * @create 2017-08-13-下午9:25
 */
public final class  PCDate { //不变模式，此类优点[数据不可变]

    private final int intData;  //final 后必须通过构造函数 初始化值/或者在初始化的时候给定值

    public PCDate(int d){
        this.intData=d;
    }

    public int getDate(){
        return intData;
    }


    @Override
    public String toString() {
        return "PCDate{" +
                "intData=" + intData +
                '}';
    }
}
