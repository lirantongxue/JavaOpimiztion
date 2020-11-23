package javaoptimization.ThreadDemo.ThreadCreateDemo;
/**
 * Created by liran on 17/8/14.
 */

import java.util.concurrent.*;

/**
 * 线程创建总结:
 *
 * @auther liran
 * @create 2017-08-14-上午10:48
 */
public class ThreadCreateDemo implements Runnable {

    public static void main(String[] args) {

//
//        threadCreateA();
//
//        threadCreateB();
//
//        threadCreateC();
//
//        threadCreateD();
//          查询电脑CPU 个数
       System.out.println(Runtime.getRuntime().availableProcessors());



    }

    private static void threadCreateC() {
        // 创建可以容纳3个线程的线程池
        System.out.println("第三种");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // 线程池的大小会根据执行的任务数动态分配
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

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


    /**
     * 方法 ThreadToolExecutorDemo
     *
     * @Auth liran
     * @Create 17/8/14 下午2:03
     * @Param []
     * 构造方法参数讲解
     * 参数名	作用
     * corePoolSize	核心线程池大小
     * maximumPoolSize	最大线程池大小
     * keepAliveTime	线程池中超过corePoolSize数目的空闲线程最大存活时间；可以allowCoreThreadTimeOut(true)使得核心线程有效时间
     * TimeUnit	keepAliveTime时间单位
     * workQueue	阻塞任务队列
     * threadFactory	新建线程工厂
     * RejectedExecutionHandler	当提交任务数超过maxmumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理
     * @Return void
     * @Description 自定义线程实现  核心实现ThreadPoolExecutor 方法
     */
    public  static void threadCreateD() {

        ThreadCreateDemo threadCreateDemo = new ThreadCreateDemo();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 4, 1000L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        threadPoolExecutor.execute(threadCreateDemo);
    }

}
