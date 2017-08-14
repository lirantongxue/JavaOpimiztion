package JavaOptimization.ThreadDemo.ProducerDemo;
/**
 * Created by liran on 17/8/13.
 */

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者:
 *
 * @auther liran
 * @create 2017-08-13-下午10:38
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCDate> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCDate> queue) {
        this.queue = queue;
    }

    public boolean isRunFlg=true;


    @Override
    public void run() {
        //打印当前线程ID
        System.out.println("start Consumer id=" + Thread.currentThread().getId());

        Random r = new Random();//随机等待时间
        try {
            while (isRunFlg) {
                PCDate pcDate = queue.take(); //提取任务 会阻塞直到队列里有值
                if (null != pcDate) {
                    int re = pcDate.getDate() * pcDate.getDate();//模拟业务 假设算平方
                    System.out.println(MessageFormat.format("{0} * {1} = {2}", pcDate.getDate(), pcDate.getDate(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));//模拟消费时间
                    if(!pcDate.runFlg){
                        this.isRunFlg=false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
