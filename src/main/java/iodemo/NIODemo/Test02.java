package iodemo.NIODemo;

import java.nio.ByteBuffer;

/**
 * 测试:
 *
 * @auther liran
 * @create 2018-09-12-上午8:34
 */
public class Test02 {


    public static void main(String[] args) {

        test();

    }

    private static void test() {
        ByteBuffer b= ByteBuffer.allocate(15);

        for (int i = 0; i <10 ; i++) {

            b.put((byte) i);

        }
        b.flip();
        System.out.println("limit"+b.limit());


        for (int i = 0; i <b.limit() ; i++) {

            System.out.print(b.get());
            if (i==4){
                b.mark();
                System.out.print("标志:"+i);
            }

        }
        //回到标志位
        b.reset();
        System.out.println("\n");
        while (b.hasRemaining()){

            System.out.print(b.get());


        }
    }

}
