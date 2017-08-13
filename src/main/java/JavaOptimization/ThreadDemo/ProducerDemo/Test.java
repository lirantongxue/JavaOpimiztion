package JavaOptimization.ThreadDemo.ProducerDemo;
/**
 * Created by liran on 17/8/13.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 测试生产者消费者:
 *
 * @auther liran
 * @create 2017-08-13-下午10:52
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCDate> queue = new LinkedBlockingDeque<PCDate>(10);//初始化队列大小

        Producer producer1 = new Producer(queue);  //建立生产者
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);

        Consumer consumer1 = new Consumer(queue); //建立消费者
        Consumer consumer2 = new Consumer(queue); //建立消费者
        Consumer consumer3 = new Consumer(queue); //建立消费者

        ExecutorService sevice = Executors.newCachedThreadPool();  //建立线程池

        sevice.execute(producer1); //运行 生产者
        sevice.execute(producer2);
        sevice.execute(producer3);

        sevice.execute(consumer1); //运行 消费者
        sevice.execute(consumer2);
        sevice.execute(consumer3);
        // queue.take(): 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
        producer1.stop();//终止生产者 生产。 [因为消费者 读取队列task 方法会阻塞直到拿新的生产者在队列中加入的业务数据]
        producer2.stop();
        producer3.stop();

        Thread.sleep(3000);
        sevice.shutdown();// 由于消费者使用 task方法 会阻塞队列，所以shutdown 方法永久不会安全关闭线程池
        System.out.println("线程池关闭");

    }
}
