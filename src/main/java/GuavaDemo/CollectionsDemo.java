package GuavaDemo;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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

/**
 * 不可变集合测试
 *
 * @author v-ranli
 * @create 2017-01-13-15:02
 */

public class CollectionsDemo {




    public static void main(String[] args) {
        FileReader reader = null;
        StringBuffer sb= new StringBuffer("");
        try {
            reader = new FileReader("G://tomcat-access-log/wxservice.log");
            BufferedReader br = new BufferedReader(reader);

            String str = null;
            List<Integer> list= Lists.newArrayList();
            float cnt=0;
            Set<String> set= new HashSet<String>();
            Map<String,Integer> evenMap= Maps.newHashMap();
            Map<String,Integer> mesTypemap=Maps.newHashMap();
            while((str = br.readLine()) != null) {
                sb.append(str+"/n");
                if(str.contains("接受微信报文")){
                    int znum=  str.indexOf("<xml>");
                    int end=  str.indexOf("/xml");
                    String newStr=  str.substring(znum,end+5);
                    System.out.println("解析XML:"+newStr);
                    String newXml="<?xml version=\"1.0\" encoding=\"utf-8\"?>"+newStr;
                    Document docment= null;
                    try {
                        docment = DocumentHelper.parseText(newXml);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    Element root=  docment.getRootElement();
                    Element msgType=root.element("MsgType");  //根节点下的子节点名

                    if(null != mesTypemap.get(msgType.getText())){
                        int cntsType =  mesTypemap.get(msgType.getText());
                        mesTypemap.put(msgType.getText(),++cntsType);
                    }else{
                        mesTypemap.put(msgType.getText(),1);
                    }

                    if("event".equals(msgType.getText())){
                        Element event=root.element("Event");  //根节点下的子节点名
                        if(null != evenMap.get(event.getText())){
                            int cnts =  evenMap.get(event.getText());
                            evenMap.put(event.getText(),++cnts);
                        }else{
                            evenMap.put(event.getText(),1);
                        }
                        if(!set.contains(event.getText())){
                            set.add(event.getText());
                        }
                    }
                }
            }
            System.out.println("event事件个数"+set.size());
            System.out.println("事件类型个数"+mesTypemap.toString());
            System.out.println("event事件详情"+evenMap.toString());
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
