package JavaOptimization.IOdemo;

import java.io.*;
import java.lang.management.MemoryManagerMXBean;

/**
 * 字符流操作:
 *
 * @auther liran
 * @create 2017-08-11-上午10:49
 */
public class ReaderOrWriter {

    public static void main(String[] args) {


        writerDemo();

        readerDemo();

        inpuStreamReader();

        dateInputStream();

        objectOutPutStream();

        objectOutputSream();

    }

    /**
     * 方法 objectOutPutStream
     *
     * @Auth liran
     * @Create 17/8/11 下午5:38
     * @Param []
     * @Return void
     * @Description 对象流测试[JAVA 序列号操作]
     */
    private static void objectOutPutStream() {

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Users/liran/Desktop/student.txt"));
            Student s1 = new Student("李冉", 24);
            Student s2 = new Student("Andy", 33);
            oos.writeObject(s1);
            oos.writeObject(s2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null)
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }

    /**
     * 方法 objectOutputSream
     *
     * @Auth liran
     * @Create 17/8/12 下午10:53
     * @Param []
     * @Return void
     * @Description 序列话Byte[] 数组 ByteArrayOutputStream 数组流进行存储
     */
    private static void objectOutputSream() {

        ObjectOutputStream ooss = null;
        ObjectInputStream obj = null;
        ByteArrayInputStream bais = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ooss = new ObjectOutputStream(baos);
            Student s2 = new Student("李冉", 33);
            ooss.writeObject(s2);

            byte[] bye = baos.toByteArray();//入库的时候，存字节数组

            bais = new ByteArrayInputStream(baos.toByteArray());
            obj = new ObjectInputStream(bais);
            Student student = (Student) obj.readObject();
            System.out.println(student.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (obj != null)
                try {
                    bais.close();
                    obj.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            if (ooss != null)
                try {
                    ooss.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 方法 dateInputStream
     *
     * @Auth liran
     * @Create 17/8/11 下午5:39
     * @Param []
     * @Return void
     * @Description 原始数据流
     */
    private static void dateInputStream() {

        DataOutputStream dataOutStream = null;
        try {

            dataOutStream = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream("/Users/liran/Desktop/writerDemo1.txt")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {// 写入文件
            dataOutStream.writeChar('a');
            dataOutStream.writeInt(3);
            dataOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 方法 inpuStreamReader
     *
     * @Auth liran
     * @Create 17/8/11 下午5:40
     * @Param []
     * @Return void
     * @Description 字节流转换字符流
     */
    private static void inpuStreamReader() {

        try {
            //键盘的最常见写法
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/liran/Desktop/writerDemo2.txt"), "UTF-8"));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                if ("over".equals(line)) break;
                bufw.write(line.toUpperCase());
                bufw.newLine();
                bufw.flush();
            }
            bufr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法 readerDemo
     *
     * @Auth liran
     * @Create 17/8/11 下午5:41
     * @Param []
     * @Return void
     * @Description 带缓冲字符流操作
     */
    private static void readerDemo() {

        try {
            FileReader fr = new FileReader("/Users/liran/Desktop/writerDemo.txt");
            BufferedReader bufferedReader = new BufferedReader(fr);

            char[] buf = new char[10];
            //将Denmo中的文件读取到buf数组中。
            int num = 0;
            //读到结尾 为 -1
            StringBuilder stringBuilder = new StringBuilder();
            String tem = bufferedReader.readLine();
            while (null != tem) {
                stringBuilder.append(tem);
                tem = bufferedReader.readLine();
            }
            System.out.println(stringBuilder);
            bufferedReader.close();
        } catch (IOException e) {
            sop(e.toString());
        }
    }

    private static void writerDemo() {
        try {
            //创建一个FileWriter对象，该对象一被初始化就必须要明确被操作的文件。
            //而且该文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
            FileWriter fw = new FileWriter("/Users/liran/Desktop/writerDemo.txt");//目的是明确数据要存放的目的地。
            //用有缓冲的装饰对象写提高效率
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/liran/Desktop/writerDemo.txt"));
            //调用write的方法将字符串写到流中
            bufferedWriter.write("liran!");
            //刷新流对象缓冲中的数据，将数据刷到目的地中
            bufferedWriter.flush();
            //关闭流资源，但是关闭之前会刷新一次内部缓冲中的数据。当我们结束输入时候，必须close();
            bufferedWriter.write("李冉");
            bufferedWriter.close();
            //flush和close的区别：flush刷新后可以继续输入，close刷新后不能继续输入。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sop(Object obj) {
        System.out.println(obj);
    }


}
