package javaoptimization.DariableDemo;/**
 * Created by liran on 17/8/9.
 */

/**
 * 局部全局变量的正确用法:
 *
 * @auther liran
 * @create 2017-08-09-下午3:32
 */
public class ObjectvariableDemo {

    public  static int a=0;

    /**
     * 方法 main
     * @Auth liran
     * @Create 17/8/9 下午3:38
     * @Param [args]
     * @Return void
     * @Description 调用方法时传递的参数或者局部变量保存在栈中，而实例变量，全局变量保存在堆中。故局部变量的方访问速度更快。
     */
    public static void main(String[] args) {

        localVariableDemo();
        overallVariableDemo();

    }
    //局部变量
    private static void overallVariableDemo() {
        //耗时统计
        long startime=System.currentTimeMillis();
        System.out.println("开始时间"+startime);
        int b=0;
        for (int i = 0; i < 1000000; i++) {
            b++;
        }
        System.out.println("耗时[毫秒]"+(System.currentTimeMillis()-startime));
    }
    //全局变量
    private static void localVariableDemo() {
        //耗时统计
        long startime=System.currentTimeMillis();
        System.out.println("开始时间"+startime);
        int a=0;
        for (int i = 0; i < 1000000; i++) {
            a++;
        }
        System.out.println("耗时[毫秒]"+(System.currentTimeMillis()-startime));
    }
}
