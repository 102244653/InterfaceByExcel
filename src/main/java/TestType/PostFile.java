package TestType;

import Tools.LoggerControler;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.util.List;
import java.util.Map;

import static HTTPRequest.Post.sendHttpPost;

/**
 * Created by Administrator on 2017/11/13.
 */
public class PostFile {
    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl
     *            地址
     * @param maps
     *            参数
     * @param fileLists
     *            附件
     */
    final static LoggerControler log = LoggerControler.getLogger(PostFile.class);
    public static String PostFile(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        log.info("正在发送POST（上传文件）请求...");
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null) {
            for (String key : maps.keySet()) {
                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
            }
        }
        if (fileLists != null) {
            for (File file : fileLists) {
                FileBody fileBody = new FileBody(file);
                meBuilder.addPart("files", fileBody);
            }
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }
}
