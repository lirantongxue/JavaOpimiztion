package JavaOptimization.SwitchDemo;/**
 * Created by liran on 17/8/9.
 */

/**
 * Switch优化:
 *
 * @auther liran
 * @create 2017-08-09-下午3:45
 */
public class SwitchOptimiztion {


    public static void main(String[] args) {
//        //原始switch耗时统计
        long startime=System.currentTimeMillis();
        System.out.println("开始时间"+startime);
        int old=0;
        for (int j = 0; j <1000000 ; j++) {
            old=swtichDemo(j);
        }
        System.out.println("耗时[毫秒]"+(System.currentTimeMillis()-startime));


        //优化后的
        //耗时统计  j<1000000 时  效率高于  swtich  当大于的时候 效率低于switch
        long startime1=System.currentTimeMillis();
        System.out.println("开始时间"+startime1);
        int re=0;
        int[] rw= new int[]{0,6,7,8,10,16,18,44};
        for (int j = 0; j <1000000 ; j++) {
            re=arryInt(rw,j);
        }
        System.out.println("耗时[毫秒]"+(System.currentTimeMillis()-startime1));




    }

    private static int arryInt(int[] sw,int z) {
        int i=z%10+1;
        if(i>7 || i <1){
            return -1;
        }else {
            return sw[i];
        }
    }

    private static int swtichDemo(int i) {
        int j=i%10+1;
        switch (j){
            case 1: return 3;
            case 2: return 6;
            case 3: return 7;
            case 4: return 8;
            case 5: return 10;
            case 6: return 16;
            case 7: return 18;
            case 8: return 44;
            default:return -1;

        }
    }

}
