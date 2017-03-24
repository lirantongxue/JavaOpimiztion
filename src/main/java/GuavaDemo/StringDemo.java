package GuavaDemo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Guava测试类-String用例
 *
 * @author v-ranli
 * @create 2017-01-13-11:27
 */

public class StringDemo {

    public static void StringJoinTest(){
    Joiner joiner=  Joiner.on("");
    System.out.println(joiner.join(new Object[]{"a","b","c"}));
    Iterable<String> d=    Splitter.on(",").trimResults().split("  d,adfe,aete,ll,dfd ");
        for(String f:d){
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        StringDemo.StringJoinTest();
    }
}
