package ProxyTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib的动态代理:
 *
 * @auther liran
 * @create 2018-08-05-下午3:05
 */
public class CglibDbqueryInterceptor implements MethodInterceptor {


    IDBQuery real=null;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if(real==null){
            real= new DBQuery();
        }

        return real.request();
    }



    public static IDBQuery createJDKProxy() {
        Enhancer enhancer= new Enhancer();

        enhancer.setCallback(new CglibDbqueryInterceptor());
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        IDBQuery cglibproxy = (IDBQuery) enhancer.create();

        return cglibproxy;

    }
}
