package TestCases;

import BaseCase.BaseCase;
import TestData.SetData;
import TestData.TestFilePath;
import TestData.UserInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Administrator on 2017/12/23.
 */
public class OtherAPI {
    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //获取国家列表
    @Test(priority = 0)
    public void getCountry()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getCountry");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getCountry", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getCountry" + (i + 1) + "执行失败");
                }
            }

    }

    //获取中国区域列表
    @Test(priority = 1)
    public void getRegion()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getRegion");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getRegion", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getRegion" + (i + 1) + "执行失败");
                }
            }

    }

    //获取支持揽收的区域
    @Test(priority = 2)
    public void getRegionForReceiving()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getRegionForReceiving");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getRegionForReceiving", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getRegionForReceiving" + (i + 1) + "执行失败");
                }
            }

    }

    //获取系统仓库
    @Test(priority = 3)
    public void getWarehouse()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getWarehouse");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getWarehouse", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getWarehouse" + (i + 1) + "执行失败");
                }
            }

    }

    //获取运输方式
    @Test(priority = 4)
    public void getShippingMethod ()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getShippingMethod");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getShippingMethod", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getShippingMethod" + (i + 1) + "执行失败");
                }
            }

    }


    //获取系统品类
    @Test(priority = 5)
    public void getCategory()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getCategory");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getCategory", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getCategory" + (i + 1) + "执行失败");
                }
            }

    }

    //获取公司账户
    @Test(priority = 6)
    public void getAccount ()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getAccount");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getAccount", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getAccount" + (i + 1) + "执行失败");
                }
            }

    }
}
