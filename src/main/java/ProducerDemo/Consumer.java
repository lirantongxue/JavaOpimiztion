package ProducerDemo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者:
 *
 * @auther liran
 * @create 2018-09-17-上午9:34
 */
public class Consumer implements Runnable {


    private BlockingQueue<PCData> queue;

    private static final int SLEEP_TIME = 1000;


    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }





    @Override
    public void run() {
        System.out.println("消费者已经启动"+Thread.currentThread().getName()+Thread.currentThread().getId());
        Random random=new Random();

        try {
            while (true){
                PCData data=queue.take();
                if(null != data){

                    int re = data.getIntData()*data.getIntData();
                    System.out.println("获取数据的职"+re);
                    Thread.sleep(random.nextInt(SLEEP_TIME));
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
