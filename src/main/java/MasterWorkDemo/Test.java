package MasterWorkDemo;

import java.util.Map;
import java.util.Set;

/**
 * 测试类:
 *
 * @auther liran
 * @create 2018-09-16-下午7:47
 */
public class Test {

    public class doToyWork extends Work {


        //真实的业务，继承Work  并重写handle 即完成真实任务的设定
        @Override
        public Object handle(Object obj) {

            Integer i = (Integer) obj;

            return i * i * i;

        }


    }


    public void test2() {

        Master master = new Master(new doToyWork(), 10);

        // 工人的任务就是  自身乘3 。     任务工厂添加 任务数量 100个。
        for (int i = 0; i < 100; i++) {
            master.submit(i);
        }
        master.excute();

        int re = 0;
        Map<String, Object> resulMap = master.getResultMap();
        while (resulMap.size() > 0 || !master.isComplete()) {
            Set<String> keys = resulMap.keySet();
            String key = null;
            for (String k : keys) {
                // 每次都将第一个key 拿出来
                key = k;
                break;
            }
            Integer i = null;
            if (key != null) {
                i = (Integer) resulMap.get(key);
            }
            if (i != null) {
                re += i;
            }
            if (key != null) {
                resulMap.remove(key);
            }
        }
        System.out.println("最终结果" + re);
    }


    public static void main(String[] args) {
        new Test().test2();
    }

}
