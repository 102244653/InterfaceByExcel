import MyReport.TestListener;
import TestData.SetData;
import TestData.TestFilePath;
import TestData.UserInfo;
import TestType.GetURL;
import TestType.PostURL;
import org.testng.annotations.*;
import BaseCase.BaseCase;

/**
 * Created by Administrator on 2017/12/13.
 */
public class MyTest {

    @Test
    public void test(){
        System.out.println(GetURL.GetURL("https://d.tigerwallet.cn"));
        //System.out.println(GetURL.GetURL("http://www.baidu.com"));
    }

}
