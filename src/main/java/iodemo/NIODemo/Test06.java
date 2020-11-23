package iodemo.NIODemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件映射到内存
 *
 * @auther liran
 * @create 2018-09-12-上午8:34
 * channel 双通道可读可写
 */
public class Test06 {


    public static void main(String[] args) {

        test();

    }

    private static void test() {

        try {
            RandomAccessFile  raf= new RandomAccessFile("/Users/liran/Desktop/redis1.conf","rw");
            FileChannel  fileChannel=  raf.getChannel();
            //
            MappedByteBuffer mbb= fileChannel.map(FileChannel.MapMode.READ_WRITE,0,raf.length());
            while (mbb.hasRemaining()){
                System.out.println((char) mbb.get());
            }

            mbb.put(0,(byte) 98);
            raf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){

        }


    }

}
