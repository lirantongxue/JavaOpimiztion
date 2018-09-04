package ProxyTest;

/**
 *
 * 动态代理测试
 * 真实主题:
 *
 * @auther liran
 * @create 2018-08-05-下午2:30
 */
public class DBQuery implements IDBQuery {


    public DBQuery(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String request() {
        return "这个主题很有意思";
    }
}
