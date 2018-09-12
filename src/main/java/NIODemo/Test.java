package NIODemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO测试:
 *
 * @auther liran
 * @create 2018-09-08-上午8:46
 */
public class Test {
    public static void main(String[] args) {

        try {
            nioCopyFile("/Users/liran/Desktop/redis.conf", "/Users/liran/Desktop/redis1.conf");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void nioCopyFile(String soure, String desting) throws Exception {
        File file=new File("/Users/liran/Desktop/redis1.conf");
        if(file == null){
            file.mkdir();
        }
        FileInputStream fileInputStream = new FileInputStream(soure);
        FileOutputStream fileOutputStream =new FileOutputStream(file);

        //读写通道
        FileChannel readChannel= fileInputStream.getChannel();

        //写文件通道
        FileChannel writeChannel= fileOutputStream.getChannel();


        ByteBuffer buffer= ByteBuffer.allocate(1024*8);
        while (true){
            buffer.clear();
            int len= readChannel.read(buffer);
            if(len ==-1){
                break;
            }
            //结束
            buffer.flip();
            //将缓存内容写入文件
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
