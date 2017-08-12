package JavaOptimization.FutureDemo;
/**
 * Created by liran on 17/8/12.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 业务处理类:
 *
 * @auther liran
 * @create 2017-08-12-下午11:35
 */
public class RealDate implements Callable<Map<String,Object>> {

    public String des;

    public  RealDate(String des){
        this.des=des;
    }
    @Override
    public Map<String,Object> call() throws Exception {
        //这里是 实际处理业务代码
        StringBuilder stringBuilder=new StringBuilder();
        //模拟业务耗时
        for (int i = 0; i <10 ; i++) {

            stringBuilder.append(i);
        }
        Thread.sleep(7000);
        System.out.println(des);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("K",stringBuilder.toString());
        return map;
    }
}
