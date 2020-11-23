package javaoptimization.ThreadDemo.ConcurrentDatastructure;
/**
 * Created by liran on 17/8/14.
 */

import java.util.Collections;
import java.util.HashSet;

/**
 * 并发set:
 *
 * @auther liran
 * @create 2017-08-14-下午4:28
 */
public class CouncurrentSet {

    public static void main(String[] args) {

        CouncurrentSetSysn();
    }

    private static void CouncurrentSetSysn() {

        Collections.synchronizedSet(new HashSet<String>());
    }
}
