package HTTPRequest;

import Tools.LoggerControler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/6.
 */
public class Get extends HttpClientUtil {
    /**
     * 发送Get请求
     * @return
     */
    final static LoggerControler log = LoggerControler.getLogger(Get.class);
    public static String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            //System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
            int statuscode=response.getStatusLine().getStatusCode();
            if (statuscode >= 300) {
                log.error("响应异常，状态码："+statuscode);
                throw new Exception(
                        "HTTP Request is not success, Response code is " + statuscode);
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, Config.CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    log.info("请求完成，释放资源...");
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

}
