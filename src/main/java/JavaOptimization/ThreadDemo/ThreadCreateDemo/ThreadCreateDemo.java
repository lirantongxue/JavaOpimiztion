package JavaOptimization.ThreadDemo.ThreadCreateDemo;
/**
 * Created by liran on 17/8/14.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 线程创建总结:
 *
 * @auther liran
 * @create 2017-08-14-上午10:48
 */
public class ThreadCreateDemo implements Runnable {

    public static void main(String[] args) {


        threadCreateA();

        threadCreateB();

        threadCreateC();

    }

    private static void threadCreateC() {
        // 创建可以容纳3个线程的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // 线程池的大小会根据执行的任务数动态分配
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        // 效果类似于Timer定时器
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    }

    private static void threadCreateB() {
        new Thread(new ThreadCreateDemo()).start();
    }

    private static void threadCreateA() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("第一种");
            }
        }).start();
    }

    @Override
    public void run() {
        System.out.println("第二种");
    }
}
