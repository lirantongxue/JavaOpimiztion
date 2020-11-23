package designpatterns.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理:
 *
 * @auther liran
 * @create 2018-08-05-下午2:47
 */
public class JdkDBqueryHandler implements InvocationHandler {


    IDBQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (real == null) {

            real = new DBQuery();
        }

        return real.request();
    }


    public static IDBQuery createJDKProxy() {
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IDBQuery.class},
                new JdkDBqueryHandler());
        return jdkProxy;

    }
}
