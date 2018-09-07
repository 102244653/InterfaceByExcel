package TestType;

import HTTPRequest.Config;
import Tools.LoggerControler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.util.Iterator;
import java.util.Map;

import static HTTPRequest.Post.sendHttpPost;

/**
 * Created by Administrator on 2017/11/13.
 */
public class PostMaps {
    /**
     * 发送 post请求
     *
     * @param maps
     *            参数
     */
    final static LoggerControler log = LoggerControler.getLogger(PostMaps.class);
    public static String PostMaps(String httpUrl, Map<String, String> maps) {
        // 创建httpPost
        log.info("开始发送POST(MAPS)请求...");
        HttpPost httpPost = new HttpPost(httpUrl);
        String parem = convertStringParamter(maps);
        try {
            // 设置参数
            if (parem != null && parem.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(parem, "UTF-8");
                stringEntity.setContentType(Config.CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }else {
                log.error("请求参数为空...");
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return  sendHttpPost(httpPost);
    }

    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     *
     * @param parameterMap
     *            需要转化的键值对集合
     * @return 字符串
     */
    public static String convertStringParamter(Map parameterMap) {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value =(String) parameterMap.get(key);
                } else {value = "";}
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        return parameterBuffer.toString();
    }
}
