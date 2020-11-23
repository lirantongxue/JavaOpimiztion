package javaoptimization.ThreadDemo.JavaProgrammingDemo;
/**
 * Created by liran on 2018/1/7.
 */

/**
 * 线程终端测试Demo:
 *
 * @auther liran
 * @create 2018-01-07-下午7:33
 */
public class InterruptDemo {

    public static void main(String[] args) {

        /**
         * interrupted 测试当前线程是否中断状态，执行后具有将状态标志清除为flase 功能。
         * isInterrupted 测试线程Thread 对象是否已经中断状态，但不清除状态标志。
         */

//        Thread.currentThread().interrupt();
//        System.out.println("是否停止1" + Thread.interrupted());
//        System.out.println("是否停止4" + Thread.currentThread().isInterrupted());
//        Thread.currentThread().interrupt();
//        System.out.println("是否停止6" + Thread.currentThread().isInterrupted());
//        System.out.println("是否停止7" + Thread.interrupted());
//        System.out.println("是否停止8" + Thread.interrupted());

      String f=  reverse("abcdefg");

        System.out.println(f);
    }

    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        System.out.println(originStr.substring(1) + originStr.charAt(0));
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
}
