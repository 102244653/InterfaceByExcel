package TestData;

import Tools.MyData;

/**
 * Created by Administrator on 2017/9/29.
 */
public class TestFilePath {
    //系统当前时间
    public static String nowtime= MyData.format(MyData.time8);
    public static String title="谷仓API测试报告";

    //谷仓237测试用例
    public static String path=System.getProperty("user.dir")+"\\TestCase\\GCTEST.xls";
//    //谷仓235测试用例
//    public static String path=System.getProperty("user.dir")+"\\TestCase\\GCSBX.xls";
//    //中邮237测试用例
//    public static String path=System.getProperty("user.dir")+"\\TestCase\\GCTEST.xls";
//    //中邮235测试用例
//    public static String path=System.getProperty("user.dir")+"\\TestCase\\GCSBX.xls";
    //测试报告输出
    public static String outpath=System.getProperty("user.dir")+"\\TestExcelReport\\Report_"+nowtime+".xls";
    //报告名称
    public static String reportname="谷仓API测试报告";

}
