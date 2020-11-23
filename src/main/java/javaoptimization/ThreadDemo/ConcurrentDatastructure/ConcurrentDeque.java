package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/15.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 并发双向队列:
 *
 *
 *
 * @auther liran
 * @create 2017-08-15-下午4:31
 */
public class ConcurrentDeque {

    public static void main(String[] args) {


        ConcurrentDeque();

    }

    private static void ConcurrentDeque() {
        Deque<String> dequeArry= new ArrayDeque<>();

        Deque<String>  dequeLink= new LinkedList<>();


        //适用高并发  性能不如：LinkedBlockingQueue  ,CouncurrentLinkQueue
        Deque<String>  dequeLinkBlock= new LinkedBlockingDeque<>();

        for (int i = 0; i <20000 ; i++) {
            dequeLinkBlock.add("i");
        }

        Iterator<String> de= dequeLinkBlock.descendingIterator();

        while (de.hasNext()){

            System.out.println("输出"+de.next());

        }

    }
}
