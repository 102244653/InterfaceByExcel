package TestType;

import Tools.LoggerControler;
import org.apache.http.client.methods.HttpPost;
import org.testng.annotations.Test;

import static HTTPRequest.Post.sendHttpPost;

/**
 * Created by Administrator on 2017/11/13.
 */
public class PostURL{
    /**
     * 发送 post请求
     * @param httpUrl
     * 地址
     */
    final static LoggerControler log = LoggerControler.getLogger(PostURL.class);
    public static String PostURL(String httpUrl) {
        // 创建httpPost
        log.info("开始发送POST(URL)请求...");
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }


}
