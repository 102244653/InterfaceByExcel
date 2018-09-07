package TestData;

import Tools.LoggerControler;
import Tools.MyExcel;

/**
 * Created by Administrator on 2017/11/14.
 */
public class SetData {
    final static LoggerControler log = LoggerControler.getLogger(SetData.class);
    public String[] testdatas;

    //获取用例数
    public int setnum(String TestFilePath,String sheetname)throws Exception{
        MyExcel work=new MyExcel(TestFilePath,sheetname);
        int row=work.getrows();
        log.info("本次执行的用例总数："+String.valueOf(row));
        return row;
    }

    //按行读取数据
    public String[] ReadTestData(String excelpath,String sheetname,int row)throws Exception{
        MyExcel work1=new MyExcel(excelpath,sheetname);
        testdatas=work1.readFromExcelrow(row);
        log.info("读取数据完成...");
        return testdatas;
    }

    //按行写入数据
    public void WriteTestData(String excelpath,String sheetname,String[] data)throws Exception{
        MyExcel work2=new MyExcel(excelpath,sheetname);
        work2.writeToExcelRows(data,45);
        work2.save(excelpath);
        log.info("写入数据完成...");
    }

    //Excel报告文件写入表头
    public void CreatReport()throws Exception{
        String[] data={"ID","TestCaseName","TestURL","RequestData","ExpectResult","ResponseData","Result"};
        new MyExcel().CreatExcel(TestFilePath.outpath,TestFilePath.title,data);
        log.info("测试报告已生成："+TestFilePath.outpath);
    }

}
