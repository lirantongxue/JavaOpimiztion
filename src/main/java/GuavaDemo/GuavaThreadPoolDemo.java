package GuavaDemo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * GUAVA线异步回调池测试
 *
 * @author v-ranli
 * @create 2017-01-22-15:09
 */

public class GuavaThreadPoolDemo {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture pool = service.submit(new Callable() {
            public String[] call() throws Exception {
                return getCusDate();
            }
        });
        Futures.addCallback(pool, new FutureCallback<String[]>() {

            public void onSuccess(String[] result) {
                System.out.println("异步任务处理二开始");
                for (String a : result) {
                    System.out.println(a);
                }
                System.out.println("异步任务处理二结束");
            }

            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
        service.shutdown();
        System.out.println("其他业务处理---start");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("业务处理一耗时" + i);
        }
        System.out.println("其他业务处理---end");
    }

    public static String[] getCusDate() {

        FileReader reader = null;
        StringBuffer sb = new StringBuffer("");
        String[] cusDateArry = new String[3];
        try {
            reader = new FileReader("G://tomcat-access-log/wxservice.log");
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            List<Integer> list = Lists.newArrayList();
            float cnt = 0;
            Set<String> set = new HashSet<String>();
            Map<String, Integer> evenMap = Maps.newHashMap();
            Map<String, Integer> mesTypemap = Maps.newHashMap();
            while ((str = br.readLine()) != null) {
                sb.append(str + "/n");
                if (str.contains("接受微信报文")) {
                    int znum = str.indexOf("<xml>");
                    int end = str.indexOf("/xml");
                    String newStr = str.substring(znum, end + 5);
                    String newXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + newStr;
                    Document docment = null;

                    docment = DocumentHelper.parseText(newXml);
                    Element root = docment.getRootElement();
                    Element msgType = root.element("MsgType");  //根节点下的子节点名

                    if (null != mesTypemap.get(msgType.getText())) {
                        int cntsType = mesTypemap.get(msgType.getText());
                        mesTypemap.put(msgType.getText(), ++cntsType);
                    } else {
                        mesTypemap.put(msgType.getText(), 1);
                    }

                    if ("event".equals(msgType.getText())) {
                        Element event = root.element("Event");  //根节点下的子节点名
                        if (null != evenMap.get(event.getText())) {
                            int cnts = evenMap.get(event.getText());
                            evenMap.put(event.getText(), ++cnts);
                        } else {
                            evenMap.put(event.getText(), 1);
                        }
                        if (!set.contains(event.getText())) {
                            set.add(event.getText());
                        }
                    }
                }
            }
            cusDateArry[0] = "event事件个数" + set.size();
            cusDateArry[1] = "事件类型个数" + mesTypemap.toString();
            cusDateArry[2] = "event事件详情" + evenMap.toString();
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return cusDateArry;
    }
}
