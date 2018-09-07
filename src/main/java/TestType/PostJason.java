package TestType;

import HTTPRequest.Config;
import TestData.UserInfo;
import Tools.LoggerControler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import static HTTPRequest.Post.sendHttpPost;

/**
 * Created by Administrator on 2017/11/13.
 */
public class PostJason {
    /**
     * 发送 post请求 发送json数据
     *
     * @param httpUrl
     *            地址
     * @param paramsJson
     *            参数(格式 json)
     *
     */
    final static LoggerControler log = LoggerControler.getLogger(PostJason.class);
    public static String PostJason(String httpUrl, String paramsJson) {
        log.info("正在发送POST（Jason）请求...");
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            if(UserInfo.USER!="null" || UserInfo.USER.trim().length()>0){
            httpPost.addHeader("Authorization", UserInfo.USER);}
            // 设置参数
            if (paramsJson != null && paramsJson.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
                stringEntity.setContentType(Config.CONTENT_TYPE_JSON_URL);
                httpPost.setEntity(stringEntity);
            }else {
                log.error("请求参数为空...");
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }
}
