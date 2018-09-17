package MasterWorkDemo;

import java.util.Map;
import java.util.Queue;

/**
 * 子任务工作类:
 *
 * @auther liran
 * @create 2018-09-16-下午7:22
 */
public class Work implements Runnable {


    //任务队列, 调用set将任务队列传给Work
    protected Queue<Object> worqQuere;
    //子任务返回结果集 master 负责给初始化
    protected Map<String, Object> resultMap;

    public Queue<Object> getWorqQuere() {
        return worqQuere;
    }

    public void setWorqQuere(Queue<Object> worqQuere) {

        this.worqQuere = worqQuere;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务具体业务处理 到时候要 重写对应的 业务  这里只是初步定义。 具体业务需要重写
    public Object handle(Object obj) {
        return obj;

    }

    @Override
    public void run() {
        while (true) {
            //获取子任务
            Object input = worqQuere.poll();
            if (input == null) {
                //如果没有任务就break
                break;
            } else {

                Object resultData = handle(input);
                resultMap.put(Integer.toString(input.hashCode()), resultData);

            }


        }


    }
}
