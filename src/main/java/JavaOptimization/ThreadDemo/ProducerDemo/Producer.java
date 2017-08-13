package JavaOptimization.ThreadDemo.ProducerDemo;
/**
 * Created by liran on 17/8/13.
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者模式
 * 
 *  生产者:
 * 
 *  基础API
 * BlockingQueue中基础API介绍
 * 
 * offer(E e): 将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
 * 
 * offer(E e, long timeout, TimeUnit unit): 将给定元素在给定的时间内设置到队列中，如果设置成功返回true, 否则返回false.
 * 
 * add(E e): 将给定元素设置到队列中，如果设置成功返回true, 否则抛出异常。如果是往限定了长度的队列中设置值，推荐使用offer()方法。
 * 
 * put(E e): 将元素设置到队列中，如果队列中没有多余的空间，该方法会一直阻塞，直到队列中有多余的空间。
 * 
 * take(): 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
 * 
 * poll(long timeout, TimeUnit unit): 在给定的时间里，从队列中获取值，如果没有取到会抛出异常。
 * 
 * remainingCapacity()：获取队列中剩余的空间。
 * 
 * remove(Object o): 从队列中移除指定的值。
 * 
 * contains(Object o): 判断队列中是否拥有该值。
 * 
 * drainTo(Collection c): 将队列中值，全部移除，并发设置到给定的集合中。
 *
 * @auther liran
 * @create 2017-08-13-下午9:07
 */
public class Producer implements Runnable {

    private volatile boolean isRuning = true;//运行状态标示

    private BlockingQueue<PCDate> queue; // 生产者、消费者的 内存缓冲区。数据从这里拿

    private static AtomicInteger count = new AtomicInteger();

    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCDate> queue) { //初始化 内存缓冲区
        this.queue = queue;
    }


    @Override
    public void run() {
        PCDate date = null;

        Random r = new Random();

        System.out.println("start producer id=" + Thread.currentThread().getId());

        try {
            while (isRuning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                date = new PCDate(count.incrementAndGet());//构造任务数据
                System.out.println(date + "is put into queue");//将任务数据放入缓冲区（队列）
                if (!queue.offer(date, 2, TimeUnit.SECONDS)) {
                    System.out.println("failed to put data" + date); //任务数据已经放入缓冲区
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        this.isRuning=false;

    }

}

