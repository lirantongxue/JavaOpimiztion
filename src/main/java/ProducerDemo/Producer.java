package ProducerDemo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者:
 *
 * @auther liran
 * @create 2018-09-17-上午8:52
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    //阻塞队列 线程安全的
    private BlockingQueue<PCData> blockingQueue;

    private static AtomicInteger count = new AtomicInteger();


    private static final int SLEEP_TIME = 10000;

    //创建时 就指明树洞
    public Producer(BlockingQueue<PCData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        PCData data = null;

        Random r = new Random();
        System.out.println("生产者已经启动"+Thread.currentThread().getName()+Thread.currentThread().getId());
        try {

            if(blockingQueue.isEmpty()){
                isRunning =true;
            }else {
                isRunning= false;
            }

            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEP_TIME));
                data=new PCData(count.incrementAndGet());
                System.out.println("将 PCData 数据放入 树洞中");

                if(blockingQueue.offer(data,2,TimeUnit.SECONDS)){
                    System.out.println("已经将PCData 放入树洞了");
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
