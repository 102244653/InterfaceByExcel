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
public class AfterSaleAPI {
    @BeforeClass()
    public void Start(){
        try{
            new UserInfo().Getuserinfo(TestFilePath.path, "USERINFO");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //新建售后单
    @Test(priority = 0)
    public void createAfterReturnOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"createAfterReturnOrder");
            if(qty==1|qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "createAfterReturnOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("createAfterReturnOrder"+(i+1)+"执行失败");
                }
            }
    }

    //修改售后单
    @Test(priority = 1)
    public void updateAfterReturnOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"updateAfterReturnOrder");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "updateAfterReturnOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("updateAfterReturnOrder" + (i + 1) + "执行失败");
                }
            }
    }

    //查询售后单
    @Test(priority = 2)
    public void searchReturnOrder()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"searchReturnOrder");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "searchReturnOrder", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("searchReturnOrder" + (i + 1) + "执行失败");
                }
            }
    }

    //获取售后单列表
    @Test(priority = 3)
    public void getAfterReturnOrders()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"getAfterReturnOrders");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "getAfterReturnOrders", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("getAfterReturnOrders" + (i + 1) + "执行失败");
                }
            }
    }

    //废弃售后单
    @Test(priority = 4)
    public void returnAfterOrderDiscard()throws Exception{
            BaseCase BS= new BaseCase();
            int qty=new SetData().setnum(TestFilePath.path,"returnAfterOrderDiscard");
            if(qty==0){return;}
            for(int i=1;i<=qty;i++) {
                try {
                BS.APITest(TestFilePath.path, "returnAfterOrderDiscard", i, TestFilePath.outpath);
                } catch (Exception e) {
                    System.out.println("returnAfterOrderDiscard" + (i + 1) + "执行失败");
                }
            }
    }


}
