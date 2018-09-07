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
public class PostXml {
    /**
     * 发送 post请求 发送xml数据
     *
     * @param httpUrl   地址
     * @param paramsXml  参数(格式 Xml)
     *
     */
    final static LoggerControler log = LoggerControler.getLogger(PostXml.class);
    public static String PostXml(String httpUrl, String paramsXml,String APIName) {
        if(paramsXml.equals("null")){
            paramsXml="";
        }
        log.info("开始发送POST(XML)请求...");
        String ParamsXml=
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\">\n" +
                        "\t<SOAP-ENV:Body>\n" +
                        "\t\t<ns1:callService>\n"+
                        paramsXml+
                        "\t\t\t<appToken>"+UserInfo.appToken+"</appToken>\n" +
                        "\t\t\t<appKey>"+ UserInfo.appKey+"</appKey>\n" +
                        "\t\t\t<service>"+APIName+"</service>\n" +
                        "\t\t</ns1:callService>\n" +
                        "\t</SOAP-ENV:Body>\n" +
                        "</SOAP-ENV:Envelope>";
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            // 设置参数
            if (ParamsXml != null && ParamsXml.trim().length() > 0) {
                //log.info(ParamsXml);
                StringEntity stringEntity = new StringEntity(ParamsXml, "UTF-8");
                stringEntity.setContentType(Config.CONTENT_TYPE_TEXT_HTML);
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
