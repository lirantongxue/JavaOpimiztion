package iodemo.NIODemo;

import java.nio.ByteBuffer;

/**
 * 缓冲区复制
 *
 * @auther liran
 * @create 2018-09-12-上午8:34
 */
public class Test03 {


    public static void main(String[] args) {

        test();

    }

    private static void test() {
        ByteBuffer b= ByteBuffer.allocate(15);

        for (int i = 0; i <10 ; i++) {

            b.put((byte)i);


        }
        ByteBuffer c= b.duplicate();

        System.out.println("复制后的缓冲区：");
        System.out.println(b);
        System.out.println(c);
        c.flip();
        System.out.println("flip后的缓冲区：");
        System.out.println(b);
        System.out.println(c);
        System.out.println("存入数据后的两个缓冲区区别：");
        c.put((byte)(100));
        System.out.println(b);
        System.out.println(c);
    }

}
