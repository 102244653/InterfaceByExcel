package TestCases;

import TestData.SetData;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/12/23.
 */
public class ExcelReport {
    @Test
    public void CreatExcelReport(){
        try {
            new SetData().CreatReport();
        }catch (Exception e){}
    }
}
