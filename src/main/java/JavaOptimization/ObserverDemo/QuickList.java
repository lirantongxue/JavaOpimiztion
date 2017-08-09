package JavaOptimization.ObserverDemo;/**
 * Created by liran on 17/7/17.
 */

import java.util.*;

/**
 * 快速访问接口:
 *
 * @auther liran
 * @create 2017-07-17-下午1:57
 */
public class QuickList implements RandomAccess {
    
    /**
    * 方法 根据类型选择迭代方法
    * @Auth liran
    * @Create 17/7/17 下午5:36
    * @Param 
    * @Return 
    * @Description 
    */
    public void TestList(){
        List list=new ArrayList();
        List list2=new LinkedList();
        if (list instanceof RandomAccess)
        {
            for (int i = 0; i < list.size(); i++){}
        }
        else{
            Iterator<?> iterator = list.iterator();
            while (iterator.hasNext()){
                iterator.next();
            }
        }
    }

    public void iteratorMap(){

        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("111", "222");

        Set<Map.Entry<String, String>> entrySet = hm.entrySet();

        Iterator<Map.Entry<String, String>> iter = entrySet.iterator();
        while (iter.hasNext())
        {

            Map.Entry<String, String> entry = iter.next();

            System.out.println(entry.getKey() + "\t" + entry.getValue());

        }
        //如果之遍历KEY
        Set<String> keys= hm.keySet();
        for (String s:keys){

        }

    }


}
