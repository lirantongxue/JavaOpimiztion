package ProxyTest;

/**
 * :
 *
 * @auther liran
 * @create 2018-08-05-下午2:53
 */
public class Test {

    public static void main(String[] args) {

             // jdk
             // System.out.println(JdkDBqueryHandler.createJDKProxy().request());

            //cglib
            System.out.println(CglibDbqueryInterceptor.createJDKProxy().request());

    }

}
