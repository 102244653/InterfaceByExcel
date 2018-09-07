package HTTPRequest;

import BaseCase.BaseCase;
import TestData.SetData;
import TestData.TestFilePath;
import Tools.LoggerControler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/13.
 */
public class Post extends HttpClientUtil {
    /**
     * 发送Post请求
     *
     * @param httpPost 参数设置
     * @return
     */
    final static LoggerControler log = LoggerControler.getLogger(Post.class);
    public static String sendHttpPost(HttpPost httpPost) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
            int Statuscode=response.getStatusLine().getStatusCode();
            log.info("状态码："+String.valueOf(Statuscode));
            if ( Statuscode>= 300) {
                log.error("接口请求失败:"+Statuscode);
                BaseCase.status=String.valueOf(Statuscode);
//                throw new Exception("HTTP Request is not success, Response code is " + Statuscode);
            }
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, Config.CHARSET_UTF_8);
                //log.info(responseContent);
                EntityUtils.consume(entity);
                BaseCase.status="200";
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        }
        return responseContent;
    }
}
