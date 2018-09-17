package MasterWorkDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 主任务收集分配中心:
 *
 * @auther liran
 * @create 2018-09-16-下午7:15
 */
public class Master {

    //任务队列
    private Queue<Object> workQuere = new ConcurrentLinkedQueue<>();
    //work线程队列
    protected Map<String, Thread> threadMap = new HashMap<>();
    //子任务处理结果集
    protected Map<String, Object> resultMap = new ConcurrentHashMap<>();

    //是否子任务都处理完毕
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }

        return true;
    }

    // 通过构造设置  手下工人的个数。
    public Master(Work work, int countWork) {

        work.setWorqQuere(workQuere);
        work.setResultMap(resultMap);

        for (int i = 0; i < countWork; i++) {

            threadMap.put(Integer.toString(i), new Thread(work, "功能人编号" + i));
        }
    }

    //提交任务
    public void submit(Object object) {

        workQuere.add(object);
    }


    //返回每个工人的结果集
    public Map<String, Object> getResultMap() {
        return resultMap;

    }

    //开始 让手下的每个工人工作
    public void excute() {

        for (Map.Entry<String, Thread> entryWork : threadMap.entrySet()) {

            entryWork.getValue().start();
        }

    }


}
