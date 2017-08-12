package JavaOptimization.FutureDemo;
/**
 * Created by liran on 17/8/12.
 */

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Futuer调用:
 *
 * @auther liran
 * @create 2017-08-12-下午11:52
 */
public class FutureTest {

    public static void main(String[] args) {

        FutureTask futureTask=new FutureTask<Map<String,Object>>(new RealDate("核心业务处理完毕"));

        //用法
        ExecutorService  executorService= Executors.newFixedThreadPool(1);
        //开启线程执行RealDate的 call 核心业务处理方法
        executorService.submit(futureTask);
        System.out.println("请求核心业务完毕");

        //主函数业务处理模拟耗时
        try {
            Thread.sleep(1000);
            System.out.println("主业务处理完毕");

            System.out.println("获取核心业务处理返回数据"+futureTask.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
