package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/14.
 */

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 并发队列:
 *
 * @auther liran
 * @create 2017-08-14-下午4:47
 */
public class ConcurrentQueue {


    public static void main(String[] args) {


        ConcurrentQueueSysn();

    }

    private static void ConcurrentQueueSysn() {

        Queue<String> queueLink= new ConcurrentLinkedQueue<String>(); //高性能

        BlockingQueue<String> blockingQueueLink= new LinkedBlockingDeque<String>();//只是简化了 线程间数据通信

        BlockingQueue<String> blockingQueueArry= new ArrayBlockingQueue<String>(10);

    }

}
