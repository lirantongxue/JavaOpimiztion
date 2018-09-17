package IndexObectDemo;

import java.lang.ref.ReferenceQueue;

/**
 * 测试类:
 *
 * @auther liran
 * @create 2018-09-13-下午1:21
 */
public class TestDemo {

    ReferenceQueue<Myobject> softQueue = null;
    protected String queueName;

    public static void main(String[] args) {


    }


    public class CheckRefQueue extends Thread {
        protected ReferenceQueue<Myobject> queue = null;
        protected String queueName;


        CheckRefQueue(ReferenceQueue<Myobject> queue, String queueName) {
            this.queue = queue;
            this.queueName = queueName;




        }


        @Override
        public void run() {
            super.run();
        }
    }

}
