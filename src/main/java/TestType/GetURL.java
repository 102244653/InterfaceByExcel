package TestType;

import HTTPRequest.Get;
import Tools.LoggerControler;
import org.apache.http.client.methods.HttpGet;

import static HTTPRequest.Get.sendHttpGet;

/**
 * Created by Administrator on 2017/11/13.
 */
public class GetURL {
    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    final static LoggerControler log = LoggerControler.getLogger(Get.class);
    public static String GetURL(String httpUrl) {
        // 创建get请求
        log.info("开始发送GET请求...");
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }
}
