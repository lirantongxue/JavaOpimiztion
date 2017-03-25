package httptest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * 短信测试
 *
 * @author v-ranli
 * @create 2017-03-24-15:12
 */



public class yunxinTest {

    private static final String APP_KEY = "b08423a22c2f944a22b3b341c72f8c92";
    private static final String APP_SECRET = "c595286dbad9";
    private static final String STRING = "https://api.netease.im/sms/sendcode.action";

    public static void main(String[] args) {
       // yunxinDemo();

        wxhttputil();

    }

    private static void wxhttputil() {

        final String nonce = UUID.randomUUID().toString().replace("-","");
        final String curTime = String.valueOf((new Date()).getTime() / 1000L);
        final String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, nonce ,curTime);//参考 计算CheckSum的java代码

        List<Pair<String, String>> headers = new ArrayList<Pair<String, String>>(){
            private static final long serialVersionUID = 1L;
            {
                add(new Pair<String, String>("AppKey", APP_KEY));
                add(new Pair<String, String>("Nonce", nonce));
                add(new Pair<String, String>("CurTime", curTime));
                add(new Pair<String, String>("CheckSum", checkSum));
                add(new Pair<String, String>("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"));
            }

        } ;
        String jo="mobile=18801488872";
        String result = HttpUtils.sendPostRequestWithHeaders(STRING,jo.toString(), headers);
        System.out.println(result);
    }

    /**
     * 云信测试
     */
    private static void yunxinDemo() {
        try {
        DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(STRING);


        String nonce = UUID.randomUUID().toString().replace("-","");
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile", "18801488872"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

          // 执行请求
            HttpResponse response = null;

            response = httpClient.execute(httpPost);
            // 打印执行结果
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
