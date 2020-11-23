package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/14.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发MAP:
 *
 * @auther liran
 * @create 2017-08-14-下午3:28
 */
public class ConcurrentMap {

    public static void main(String[] args) {

        HashMapSysn();

        ConcrentMap();

    }

    /**
     * 方法 ConcrentMap
     * @Auth liran
     * @Create 17/8/14 下午4:26
     * @Param []
     * @Return void
     * @Description  JDK 提供的专用于高并发的map 性能比synchronizedMap 要好
     */
    private static void ConcrentMap() {

       Map<String,Object> map= new ConcurrentHashMap<String,Object>();
    }

    /**
     * 方法 HashMapSysn
     * @Auth liran
     * @Create 17/8/14 下午4:20
     * @Param []
     * @Return void
     * @Description 同理，可以用synchronizedMap 包装MAP达到线程安全 但是在高并发的时候表现不尽人意
     */
    private static void HashMapSysn() {


        Map<String, Object> map = new HashMap<String, Object>();

        Collections.synchronizedMap(map);
    }


}
