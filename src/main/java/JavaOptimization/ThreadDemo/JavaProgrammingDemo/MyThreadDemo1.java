package JavaOptimization.ThreadDemo.JavaProgrammingDemo;
/**
 * Created by liran on 2017/12/23.
 */

/**
 * 多线程测试:
 *
 * @auther liran
 * @create 2017-12-23-下午8:44
 */
public class MyThreadDemo1 extends Thread {

    private int count = 5;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由" + this.currentThread().getName() + "计算，count=" + count);
    }

    /**
     * 方法 main
     *
     * @Auth liran
     * @Create 2017/12/23 下午8:52
     * @Param [args]
     * @Return void
     * @Description
     * 由B计算，count=3
     * 由C计算，count=2
     * 由A计算，count=3
     * 由D计算，count=1
     * Process finished with exit code 0
     *
     * 现成的启动顺序不会按照start的 执行先后顺序而定
     */
    public static void main(String[] args) {

        MyThreadDemo1 myThreadDemo1 = new MyThreadDemo1();
        Thread a = new Thread(myThreadDemo1, "A");
        Thread b = new Thread(myThreadDemo1, "B");
        Thread c = new Thread(myThreadDemo1, "C");
        Thread d = new Thread(myThreadDemo1, "D");
        a.start();
        b.start();
        c.start();
        d.start();


    }
}
