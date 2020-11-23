package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/14.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发list:
 *
 * @auther liran
 * @create 2017-08-14-下午3:28
 */
public class ConcurrentList {


    public static void main(String[] args) {
        ArryListSyns();

        CopyOrWriteList();

    }
    /**
     * 方法 CopyOrWriteList
     * @Auth liran
     * @Create 17/8/14 下午3:46
     * @Param []
     * @Return void
     * @Description 从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，
     * 才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。
     * 在没有写操作的时候，由于共享同一个内容所以不需要加锁。 不过写操作的时候会Copy副本，所以性能比较差
     */
    private static void CopyOrWriteList() {

        //核心思想利用了对象的不可变性。
        List<String> list = new CopyOnWriteArrayList<>();

    }

    private static void ArryListSyns() {
        //非线程安全 容器。 如非要使用ArrayList 实现
        List<String> list= new ArrayList<String>();

        //可以使用 synchronizedList 进行包装  实现线程安全
        Collections.synchronizedList(list);

    }
}
