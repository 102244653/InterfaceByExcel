package BaseCase;

import TestType.*;
import TestData.SetData;
import Tools.LoggerControler;
import Tools.XmlToJason;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/11/14.
 */
public class BaseCase {
    final static LoggerControler log = LoggerControler.getLogger(BaseCase.class);
    public static String status;
     String ID;//用例编号
     String TestCase;//用例名称
     public String APIName;//接口名称
     String PostMethod;//请求方法
     String TestURL;//测试环境
     String Request;//请求参数
     String ExpectASK;//ASK预期结果
     String ExpectValue;//Value预期结果
     String IsDo;//是否执行当前用例
     String URL;//接口地址
     String Response;//响应内容
     String result;//测试结果
     public String[] PutHtml=new String[10];//写入Html报告的数据
     public String[] PutExcel=new String[10];//写入Excel报告的数据

    //执行测试
    public void APITest(String TestFilePath,String sheet,int row,String TestReportPath)throws Exception{
        //读取测试数据
        SetData st=new SetData();
        String[] value=st.ReadTestData(TestFilePath,sheet,row);
        ID=value[0].trim();
        TestCase=value[1].trim();
        APIName=value[2].trim();
        PostMethod=value[3].trim();
        TestURL=value[4].trim();
        Request=value[5].trim();
        ExpectASK=value[6].trim();
        ExpectValue=value[7].trim();
        IsDo=value[8].trim();

        //判断用例是否执行
        if(IsDo.trim().equals("F")){
            Response="null";
            result=ID+TestCase+"本次不执行";
            log.info(result);
            return;
        }
        //获取测试地址
        if(String.valueOf(TestURL).equals("TEST")){
             URL="http://192.168.10.237:61602/default/svc/web-service";
        }else if(String.valueOf(TestURL).equals("SBX")){
             URL="http://202.104.134.94:7181/default/svc/web-service";
        }else {
             URL="测试地址错误："+TestURL;
            log.error(URL);
        }
        //执行请求
        switch(PostMethod){
            case "PostJason":
                Response=PostJason.PostJason(URL,Request);
                //result=this.CheckJason(Response);
                break;
            case "PostXml":
                JSONObject response= XmlToJason.Getjason(PostXml.PostXml(URL, Request, APIName));
                Thread.sleep(1000);
                if(status.equals("200")){
                    //将xml转换成jason并去除 \
                    Response=response.toString().replace("\\\"","\"");
                    result=this.CheckXml(Response);
                }else {
                    Response="接口请求失败,状态码:"+status;
                    result="FAIL";
                }
                break;
            case "PostURL":
                Response= PostURL.PostURL(URL);
                break;
            case "GetURL":
                Response= GetURL.GetURL(Request);
                break;
            default:
                log.error("读取到的请求方法错误:" + PostMethod);
                break;
        }
        log.info("测试结果："+Response);
        //Excel报告数据
        PutExcel[0]=ID;PutExcel[1]=TestCase;PutExcel[2]=URL;PutExcel[3]=Request;PutExcel[4]=ExpectASK+"+"+ExpectValue;PutExcel[5]=Response;PutExcel[6]=result;
        //html报告数据
        PutHtml[0]=TestCase;PutHtml[1]=Request;PutHtml[2]=Response;PutHtml[3]=result;PutHtml[4]=ExpectASK+"+"+ExpectValue;
        //log.info(String.valueOf(output));
        //写报告
        new SetData().WriteTestData(TestReportPath, "Sheet1", PutExcel);
        Thread.sleep(1000);
    }


    //Xml响应结果的判断
    public String CheckXml(String str){
        String testflag;
        if(ExpectValue.equals("null")){
            if(str.contains(ExpectASK)){
                testflag="PASS";
                log.info(TestCase+"   测试通过");
                System.out.println();
            }else {
                testflag="FAIL";
                log.error(TestCase+"   测试失败");
                System.out.println();
            }
        }else {
            if(str.contains(ExpectASK) && str.contains(ExpectValue)){
                testflag="PASS";
                log.info(TestCase+"   测试通过");
                System.out.println();
            }else {
                testflag="FAIL";
                log.error(TestCase+"   测试失败");
                System.out.println();
            }
        }
        return result=testflag;
    }

//    //Jason响应结果的判断
//    public String CheckJason(String str){
//        String testflag;
//        if(){
//
//        }else {
//
//        }
//        return testflag;
//    }

    public String[] GetPutHtml(){return this.PutHtml=PutHtml;}
    public String[] GetPutExcel(){return this.PutExcel=PutExcel;}
    public String GetAPIName(){
        return this.APIName=APIName;
    }

}
