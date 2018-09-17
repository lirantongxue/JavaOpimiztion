package NIODemo;

import java.nio.ByteBuffer;

/**
 * 只读缓冲区
 *
 * @auther liran
 * @create 2018-09-12-上午8:34
 */
public class Test05 {


    public static void main(String[] args) {

        test();

    }

    private static void test() {
        ByteBuffer b= ByteBuffer.allocate(15);
        for (int i = 0; i <10 ; i++) {
            b.put((byte)i);
        }
        System.out.println(b.position());
        ByteBuffer readOnlyBuffer=b.asReadOnlyBuffer();//创建只读缓冲区，数据共享缓冲区b  .但是 只读缓冲区没有改操作。
        readOnlyBuffer.flip();
        System.out.println(readOnlyBuffer.position());
        while (readOnlyBuffer.hasRemaining()){

            System.out.print(readOnlyBuffer.get()+" ");

        }
        System.out.println(readOnlyBuffer.position());
        b.put(2,(byte)(20));
        readOnlyBuffer.flip();

        while (readOnlyBuffer.hasRemaining()){
            System.out.print(readOnlyBuffer.get()+" ");
        }


    }

}
