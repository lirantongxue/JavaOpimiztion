package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/16.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile标识:
 *
 * @auther liran
 * @create 2017-08-16-下午3:17
 */
public class VolatileDemo implements Runnable {

    AtomicInteger  atomicInteger=new AtomicInteger();

    private String name;

    @Override
    public void run() {


            for (int i = 0; i < 10; i++) {
                System.out.println("线程ID:"+Thread.currentThread().getName() + "运行  :  " + atomicInteger.incrementAndGet());
                try {
                    Thread.sleep((int) Math.random() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }



    public static void main(String[] args) {

    ExecutorService  executorService=Executors.newCachedThreadPool();

        VolatileDemo  volatileDemo=new VolatileDemo();

        for (int i = 0; i <3 ; i++) {
            executorService.execute(volatileDemo);
        }
    executorService.shutdown();
    }
}


