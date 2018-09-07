package TestData;

import Tools.MyExcel;

/**
 * Created by Administrator on 2017/12/20.
 */
public class UserInfo {
    public static String appToken;
    public static String appKey;
    public static String USER;//"Basic U3JtVXNlcjoxUUFaMldTWA=="

    public void Getuserinfo(String path,String sheet){
        MyExcel my=new MyExcel(path,sheet);
        appToken=my.readFromExcelCell(0,1);
        appKey=my.readFromExcelCell(0,3);
        USER=my.readFromExcelCell(0,5);
    }

}
