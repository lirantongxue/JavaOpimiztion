package javaoptimization.ArryCopyDemo;/**
 * Created by liran on 17/8/9.
 */

/**
 * 数组拷贝优化:
 *
 * @auther liran
 * @create 2017-08-09-下午4:23
 */
public class ArryCopyOptimizatiom {



    /**
     * 方法 main
     * @Auth liran
     * @Create 17/8/9 下午4:43
     * @Param [args]
     * @Return void
     * @Description 当size <100000 次时  数组拷贝函数速度较快  反正 > 100000 时手动拷贝较快
     *
     * System.arraycopy [是浅复制，他们指向的是同一个内存地址]
     */
    public static void main(String[] args) {

        arryCopy();

       new ArryCopyOptimizatiom().objArryCopy();

    }


    /**
     * 方法 objArryCopy
     * @Auth liran
     * @Create 17/8/9 下午5:59
     * @Param []
     * @Return void
     * @Description  浅拷贝事例
     */
    private  void objArryCopy() {

        Te te= new ArryCopyOptimizatiom.Te("韩梅梅");

        Te[] array =new Te[1];
        array[0]=te;
        Te[] arraydst =new Te[1];
        System.out.println("array修改前："+array[0]);
        // 耗时统计 用拷贝数组拷贝函数
        System.arraycopy(array, 0, arraydst, 0, 1);
        array[0].name="李雷";
        System.out.println("tarray修改后："+array[0]);
        //调用
        System.out.println("arraydst："+arraydst[0]);
        arraydst[0].name="雷锋";
        System.out.println("tarray修改后："+array[0]);

    }

    private static void arryCopy() {
        int size = 10000;
        int[] array = new int[size];
        int[] arraydst = new int[size];

        for (int i = 0, j = array.length; i < j; i++) {
            array[i] = i;
        }

        // 耗时统计 用拷贝数组拷贝函数
        long startime = System.currentTimeMillis();
        System.out.println("开始时间" + startime);
        for (int k = 0; k < 1000; k++)
            System.arraycopy(array, 0, arraydst, 0, size);
        System.out.println("耗时[毫秒]" + (System.currentTimeMillis() - startime));

        //耗时统计 自己实现拷贝
        long startime1 = System.currentTimeMillis();
        System.out.println("开始时间" + startime1);
        for (int k = 0; k < 1000; k++) {
            for (int i = 0; i < size; i++) {
                arraydst[i]=array[i];
            }
        }
        System.out.println("耗时[毫秒]" + (System.currentTimeMillis() - startime1));
    }


    class Te{

        public  String name;

        Te(String name){
            this.name=name;
        }
        @Override
        public String toString() {
            return "Te{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
