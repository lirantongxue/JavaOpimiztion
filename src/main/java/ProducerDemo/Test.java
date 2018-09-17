package ProducerDemo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * :测试类
 *
 * @auther liran
 * @create 2018-09-17-上午9:40
 */
public class Test {





    public static void main(String[] args) {

        BlockingQueue<PCData> queue = new LinkedBlockingDeque<PCData>();
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        Producer p4 = new Producer(queue);


        Consumer c1= new Consumer(queue);
        Consumer c2= new Consumer(queue);
        Consumer c3= new Consumer(queue);
        Consumer c4= new Consumer(queue);

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(p1);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
        service.execute(c4);

        service.shutdown();

    }
}
