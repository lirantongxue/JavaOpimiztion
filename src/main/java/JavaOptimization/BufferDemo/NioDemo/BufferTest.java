package JavaOptimization.BufferDemo.NioDemo;/**
 * Created by liran on 17/8/7.
 */

import java.nio.ByteBuffer;

/**
 * Buffer的基本原理:
 *
 * @auther liran
 * @create 2017-08-07-上午10:30
 */
public class BufferTest {


    public static void main(String[] args) {

       // byterBufferDemo();
        duplicatDemo();


    }

    /**
     * 方法 duplicatDemo
     * @Auth liran
     * @Create 17/8/7 下午3:15
     * @Param []
     * @Return void
     * @Description 缓存拷贝
     */
    private static void duplicatDemo() {


        ByteBuffer b= ByteBuffer.allocate(15);
        for (int i = 0; i < 10 ; i++) {
            b.put((byte)i);
        }

        ByteBuffer c = b.duplicate();//拷贝
        System.out.println("B.duplicat 拷贝给c后");
        System.out.println(b);
        System.out.println(c);
        c.flip();
        System.out.println("c flip 后");
        System.out.println(b);
        System.out.println(c);
        c.put((byte) 100);
        System.out.println("c put 100 后");
        System.out.println("b.get(0)="+b.get(0));
        System.out.println("c.get(0)="+c.get(0));
    }


    /**
     * 方法 byterBufferDemo
     * @Auth liran
     * @Create 17/8/7 下午3:14
     * @Param []
     * @Return void
     * @Description buffer 基本用户 注意  clear  dulip  rewind
     */
    private static void byterBufferDemo() {

        ByteBuffer b= ByteBuffer.allocate(15);
        System.out.println("limit="+b.limit()+"capacity="+b.capacity()+"position="+b.position());
        for (int i = 0; i <10 ; i++) {
            b.put((byte)i);
        }
        System.out.println("limit="+b.limit()+"capacity="+b.capacity()+"position="+b.position());
        b.flip();//BUffer 重置清空
        System.out.println("重置Buffer后");
        System.out.println("limit="+b.limit()+"capacity="+b.capacity()+"position="+b.position());
        for (int i = 0; i <5 ; i++) {
            System.out.print(b.get());
        }
        System.out.println();
        System.out.println("limit="+b.limit()+"capacity="+b.capacity()+"position="+b.position());
        b.clear();
        System.out.println();
        System.out.println("重置Buffer后");
        System.out.println("limit="+b.limit()+"capacity="+b.capacity()+"position="+b.position());
    }


}
