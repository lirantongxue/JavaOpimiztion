package NIODemo;

import java.nio.ByteBuffer;

/**
 * 缓冲区分片
 *
 * @auther liran
 * @create 2018-09-12-上午8:34
 */
public class Test04 {


    public static void main(String[] args) {

        test();

    }

    private static void test() {
        ByteBuffer b= ByteBuffer.allocate(15);

        for (int i = 0; i <10 ; i++) {
            b.put((byte) i);
        }
        b.position(2);
        b.limit(6);
        System.out.println(b);
        ByteBuffer subBuffer= b.slice();//生成子缓冲区
        System.out.println("子缓冲区："+subBuffer.capacity());
        for (int i = 0; i <subBuffer.capacity() ; i++) {
            byte bb=subBuffer.get(i);
            bb*=10;
            subBuffer.put(bb);
        }

        b.position(0);
        b.limit(b.capacity());

        // 子﹣ 父   缓冲区 分片测试
        while (b.hasRemaining()){
            System.out.print(b.get()+" ");
        }
    }

}
