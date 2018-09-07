
import BaseCase.BaseCase;
import MyReport.TestListener;
import TestData.SetData;
import TestData.TestFilePath;
import TestData.UserInfo;
import TestType.PostJason;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * Created by Administrator on 2017/11/13.
 */
public class test {
    //@Test
//    public void  test() {
//        String url="http://202.104.134.94:7181/default/svc/web-service";
//        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://www.example.org/Ec/\">\n" +
//                "\t<SOAP-ENV:Body>\n" +
//                "\t\t<ns1:callService>\n" +
//                "\t\t\t<appToken>4d896d4b31c515f0eb0f01206812296a</appToken>\n" +
//                "\t\t\t<appKey>1e98476b71144f04e80602bac71a8e55</appKey>\n" +
//                "\t\t\t<service>getAccount</service>\n" +
//                "\t\t</ns1:callService>\n" +
//                "\t</SOAP-ENV:Body>\n" +
//                "</SOAP-ENV:Envelope>";
//        String jason="{\"Body\":{\"callService\":{\"appKey\":\"1e98476b71144f04e80602bac71a8e55\",\"appToken\":\"4d896d4b31c515f0eb0f01206812296a\",\"service\":\"getAccount\"}}}";
//        String xmlstr=PostXml.PostXml(url, xml);
//
//        System.out.println(XmlToJason.Getjason(xmlstr));
//
//    }


////
//    @Test
//    public void check(){
//        String testflag;
//        String ExpectASK="Success";
//        String ExpectValue="null";
//        String str="{\"Body\":{\"callServiceResponse\":{\"response\":\"{\"ask\":\"Success\",\"message\":\"Success\",\"pagination\":{\"page\":1,\"pageSize\":\"1\"},\"count\":\"260\",\"nextPage\":\"true\",\"data\":[{\"country_code\":\"AF\",\"country_name\":\"阿富汗\",\"country_name_en\":\"AFGHANISTAN\"}]}\"}}}";
//
//        try{
//            if(ExpectValue.trim() == "null"){
//                if(str.contains(ExpectASK.trim())){
//                    testflag="PASS";
//                    System.out.print(testflag);
//                }else {
//                    testflag="FAIL";
//
//                    System.out.print(testflag);
//                }
//            }else {
//                if(str.contains(ExpectASK.trim()) & str.contains(ExpectValue.trim())){
//                    testflag="PASS";
//
//                    System.out.print(testflag);
//                }else {
//                    testflag="FAIL";
//
//                    System.out.print(testflag);
//                }
//            }
//
//        }catch (Exception E){
//
//        }
//}

    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test(priority = 0)
    public void CreatExcelReport(){
        try {
            new SetData().CreatReport();
        }catch (Exception e){}
    }

    //获取支持揽收的区域
    @Test(priority = 1)
    public void getRegionForReceiving(){
        try {
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getRegionForReceiving");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                BS.APITest(TestFilePath.path, "getRegionForReceiving", i, TestFilePath.outpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void getWarehouse(){
        try {
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getWarehouse");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                BS.APITest(TestFilePath.path, "getWarehouse", i, TestFilePath.outpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
